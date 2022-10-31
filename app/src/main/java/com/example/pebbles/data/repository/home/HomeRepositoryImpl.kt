package com.example.pebbles.data.repository.home

import android.app.Application
import android.util.Log
import com.example.pebbles.data.db.PEBBLEDataBase
import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository
import com.example.pebbles.network.RetrofitInstance
import com.example.pebbles.network.home.HomeInterface
import com.example.pebbles.util.getJwt
import com.example.pebbles.util.getUserIdx

class HomeRepositoryImpl(val habitDAO: HabitDAO) : HomeRepository {

    //얘네를 매개변수로 받으면 더 좋지 않을까 ?
    val homeService = RetrofitInstance.getRetrofit.create(HomeInterface::class.java)

    //서버로부터 HabitList 결과 값 받아옴
    override suspend fun getHabitsFromAPI(): ArrayList<Habit>? {
        lateinit var habitList : ArrayList<Habit>
        try{
            //Service 통해서 가져오기 -> 추후에 userId, JWT 토큰
            val response = homeService.getHabits(getUserIdx() , getJwt())
            val body = response.body()
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
    suspend fun getHabitsFromDB() : ArrayList<Habit>{
        lateinit var habitList : ArrayList<Habit>
        try{
            habitList = habitDAO.getHabits()

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

    //TodoList 변경하기

    //오늘날짜 조회하기



}