package com.todaypebble.pebbles.data.repository.home.datasourceImpl

import com.todaypebble.pebbles.data.model.HabitDAO
import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.data.repository.home.datasource.HabitLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HabitLocalDataSourceImpl @Inject constructor(private val habitDAO: HabitDAO) : HabitLocalDataSource {

    override suspend fun getHabits(): List<Habit>  = habitDAO.getHabits()

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

    override suspend fun getToday(): String = habitDAO.getToday()

    override suspend fun updateTodo(habit: Habit) {
        habitDAO.updateTodo(habit)
    }
}