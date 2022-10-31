package com.example.pebbles.data.repository.home.datasource

import com.example.pebbles.data.remote.dto.HabitList
import com.example.pebbles.data.remote.model.Habit
import retrofit2.Response

//Retrofit으로 Repository 내에서 API 호출 이용하는 DataSource
interface HabitRemoteDataSource {

    suspend fun getHabits() : Response<HabitList>

}