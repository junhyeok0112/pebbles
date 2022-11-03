package com.example.pebbles.data.repository.home.datasource

import com.example.pebbles.data.remote.model.Habit

//로컬 디비 접근하는 DataSource 일단 저장되어 있는 데이터 호출하는 함수만 구현해보기
interface HabitLocalDataSource {

    suspend fun getHabits() : List<Habit>
    suspend fun saveHabitsToDB(habits : ArrayList<Habit>)       //당일 HabitList 저장
    suspend fun clearAll()
    suspend fun getToday() : String
    suspend fun updateTodo(habit : Habit)

}