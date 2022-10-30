package com.example.pebbles.data.model

import androidx.room.Insert
import androidx.room.Query
import com.example.pebbles.data.remote.model.Habit

//RoomDB에 해빗 리스트 관리하는 DAO
interface HabitDAO {
    //오늘 날짜의 Habit 리스트 저장

    @Insert()
    suspend fun saveHabit(habits:List<Habit>)

    @Query("DELETE FROM Habit_List")
    suspend fun deleteAllHabit()

    @Query("SELECT * FROM Habit_List")
    suspend fun getHabits() : List<Habit>

}