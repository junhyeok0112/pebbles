package com.example.pebbles.domain.repository

import com.example.pebbles.data.remote.model.Habit

interface HomeRepository {

    suspend fun getHabitsFromAPI() : ArrayList<Habit>?
    suspend fun getHabitsFromDB() : List<Habit>?
    suspend fun updateHabitsToDB() : List<Habit>?

}