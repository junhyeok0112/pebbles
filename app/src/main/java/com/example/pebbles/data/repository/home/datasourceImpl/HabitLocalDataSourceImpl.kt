package com.example.pebbles.data.repository.home.datasourceImpl

import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.data.repository.home.datasource.HabitLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitLocalDataSourceImpl @Inject constructor(private val habitDAO: HabitDAO) : HabitLocalDataSource {

    override suspend fun getHabits(): ArrayList<Habit>  = habitDAO.getHabits()

    override suspend fun saveHabitsToDB(habits: ArrayList<Habit>) {
        CoroutineScope(Dispatchers.IO).launch {
            habitDAO.saveHabit(habits)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch{
            habitDAO.deleteAllHabit()
        }
    }
}