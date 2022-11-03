package com.example.pebbles.data.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pebbles.data.remote.model.Habit

//RoomDB에 해빗 리스트 관리하는 DAO
@Dao
interface HabitDAO {
    //오늘 날짜의 Habit 리스트 저장
    //SaveHabitToDB로 사용
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHabit(habit:List<Habit>)

    @Query("DELETE FROM Habit_List")
    suspend fun deleteAllHabit()

    //전체 조회 및 내부 로직에서 새로 갱신
    @Query("SELECT * FROM Habit_List")
    suspend fun getHabits() : List<Habit>

    //RoomDB의 날짜 조회 -> 오늘 데아터만 저장 될거임
    @Query("SELECT DISTINCT today FROM Habit_List")
    suspend fun getToday() : String

    data class TodayTuple(
        @ColumnInfo(name = "today") val today:String?
    )

    //Todo 아이템 변경시키기 -> 쿼리 짜야함
}