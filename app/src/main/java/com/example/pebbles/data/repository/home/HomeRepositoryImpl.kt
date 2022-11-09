package com.example.pebbles.data.repository.home

import android.util.Log
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository
import com.example.pebbles.network.RetrofitInstance
import com.example.pebbles.data.remote.api.HomeService
import com.example.pebbles.data.remote.dto.logout.WithdrawalResponse
import com.example.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.example.pebbles.data.remote.dto.update.HomeUpdateResponse
import com.example.pebbles.data.repository.home.datasource.HabitLocalDataSource
import com.example.pebbles.data.repository.home.datasource.HabitRemoteDataSource
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val habitRemoteDataSource: HabitRemoteDataSource,
    private val habitLocalDataSource: HabitLocalDataSource
) : HomeRepository {

    //얘네를 매개변수로 받으면 더 좋지 않을까 ? -> 이렇게 만들 예정
    val homeService = RetrofitInstance.getRetrofit.create(HomeService::class.java)

    //서버로부터 HabitList 결과 값 받아옴
    override suspend fun getHabitsFromAPI(): ArrayList<Habit>? {
        lateinit var habitList : ArrayList<Habit>
        try{
            //Service 통해서 가져오기 -> 추후에 userId, JWT 토큰 , Datasource단에서 매개변수 처리됨
            val response = habitRemoteDataSource.getHabits()
            val body = response.body()
            Log.d("Repository" , "레포 실행 : ${response.code()} 코드 , ${response.body()} 바디")
            if(body != null){
                //넘어온 List 셋팅
                habitList = body.result.habits
                Log.d("HomeRepository- TEST" , "넘어온 List ${habitList}")
            }
        }catch(e: Exception){
            Log.d("HomeRepository_Exception" , e.message.toString())
        }

        return habitList
    }


    //RoomDB에 저장되어 있는 데이터들 꺼내오기 -> POST 요청할 때 사용
    //getHabit으로 RoomDB에 알맞은 값 셋팅하고 가져옴
    override suspend fun getHabitsFromDB() : List<Habit>{
        var habitList : ArrayList<Habit> = ArrayList()      //Local에 오늘 값 없을 수 도 있으므로
        try{
            habitList = habitLocalDataSource.getHabits() as ArrayList<Habit>
            Log.d("HomeRepositroy_Local_test" , "${habitList}")
        }catch(e: Exception){
            Log.d("HomeRepository_Exception_DB" , e.message.toString())
        }

        //이게 값이 있고 오늘 날짜면 바로 리스트 리턴
        //값이 없거나 오늘날짜가 아니면 새로운 값 받아옴.
        if(habitList.size > 0 && habitList[0].today == LocalDate.now().toString()){
           return habitList
        } else{
            //만약 없으면 오늘자 데이터 가져와서 저장하고 그값 리턴 , 지금은 임시로 외부에서 가져옴
            val temp_habitList = getHabitsFromAPI()!!
            Log.d("Repository_save_habitList_1" , "${habitList.toString()}")
            for(cur in temp_habitList){
                if(cur.today == LocalDate.now().toString()){    //오늘 날짜 아닌 애들 제거
                    habitList.add(cur)
                }
            }
            Log.d("Repository_save_habitList_2" , "${habitList.toString()}")
            //habitList를 RoomDB에 저장하기
            Log.d("Repository_save_habitList_3" , "${habitList.toString()}")
            habitLocalDataSource.clearAll()
            habitLocalDataSource.saveHabitsToDB(habitList)
        }
        return habitList
    }

    //RoomDB에 오늘날짜 저장하기 ->CoroutineScope
    override suspend fun updateHabitsToDB(): ArrayList<Habit>? {
        //데이터들 가공해서 오늘 날짜것만 넣어야함
        val tempList = getHabitsFromAPI()
        val newListOfHabits = ArrayList<Habit>()

        if (tempList != null) {
            for(cur in tempList){
                if(cur.today == LocalDate.now().toString()) newListOfHabits.add(cur)
            }
        }
        habitLocalDataSource.clearAll()
        habitLocalDataSource.saveHabitsToDB(newListOfHabits)
        return newListOfHabits
    }

    //TodoList 변경하기
    override suspend fun updateTodoToDB(habit: Habit) {
        habitLocalDataSource.updateTodo(habit)
    }

    //오늘날짜 조회하기
    override suspend fun getTodayFromDB(): String = habitLocalDataSource.getToday()

    //Habits들 API 로 갱신하기
    override suspend fun updateHabitToAPI(updateRequest: HomeUpdateRequest) : Response<HomeUpdateResponse> = habitRemoteDataSource.updateHabits(updateRequest)

    //로그아웃 , dataSource를 만들어야하나..
    override suspend fun withdrawal(userId: Int): Response<WithdrawalResponse> = habitRemoteDataSource.withdrawal(userId)
}