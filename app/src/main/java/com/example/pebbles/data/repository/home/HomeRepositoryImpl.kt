package com.example.pebbles.data.repository.home

import android.util.Log
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository
import com.example.pebbles.network.RetrofitInstance
import com.example.pebbles.data.remote.api.HomeService
import com.example.pebbles.data.repository.home.datasource.HabitLocalDataSource
import com.example.pebbles.data.repository.home.datasource.HabitRemoteDataSource
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
    override suspend fun getHabitsFromDB() : List<Habit>{
        lateinit var habitList : List<Habit>
        try{
            habitList = habitLocalDataSource.getHabits()
            Log.d("HomeRepositroy-Local_test" , "${habitList}")
        }catch(e: Exception){
            Log.d("HomeRepository_Exception_DB" , e.message.toString())
        }
        if(habitList.size > 0){
            return habitList
        } else{
            //만약 없으면 오늘자 데이터 가져와서 저장하고 그값 리턴 , 지금은 임시로 외부에서 가져옴
            habitList = getHabitsFromAPI()!!
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


    //오늘날짜 조회하기
    override suspend fun getTodayFromDB(): String = habitLocalDataSource.getToday()

}