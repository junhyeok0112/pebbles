package com.example.pebbles.domain.repository

import com.example.pebbles.data.remote.model.Habit

interface HomeRepository {

    suspend fun getHabitsFromAPI() : ArrayList<Habit>?
    suspend fun getHabitsFromDB() : ArrayList<Habit>?
    suspend fun updateHabitsToDB() : ArrayList<Habit>?

}