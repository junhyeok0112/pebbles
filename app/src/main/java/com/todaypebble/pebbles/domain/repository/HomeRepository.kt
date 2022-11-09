package com.todaypebble.pebbles.domain.repository

import com.todaypebble.pebbles.data.remote.dto.logout.WithdrawalResponse
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateResponse
import com.todaypebble.pebbles.data.remote.model.Habit
import retrofit2.Response

interface HomeRepository {

    suspend fun getHabitsFromAPI() : ArrayList<Habit>?
    suspend fun getHabitsFromDB() : List<Habit>?
    suspend fun updateHabitsToDB() : List<Habit>?
    suspend fun getTodayFromDB() : String
    suspend fun updateTodoToDB(habit : Habit)
    suspend fun updateHabitToAPI(updateRequest: HomeUpdateRequest) : Response<HomeUpdateResponse>
    suspend fun withdrawal(userId: Int): Response<WithdrawalResponse>

}