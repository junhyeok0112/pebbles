package com.example.pebbles.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.data.remote.model.Habit
import com.google.gson.Gson


@Database(entities = [Habit::class], version = 1, exportSchema = false)
@TypeConverters(value = [TodoListTypeConverter::class])
abstract class PEBBLEDataBase : RoomDatabase() {

    abstract fun habitDao(): HabitDAO

    //DB를 싱글톤으로 만들어서 메모리 관리해야함 -> 이 때 Application에 따로 할필요 없이 여기서 Companion object로 처리 -> 공식문서
    //이 DB.DAO로 접근 가능
    companion object {

        val gson = Gson()

        fun getInstance(context: Context): PEBBLEDataBase {

            return Room.databaseBuilder(
                context,
                PEBBLEDataBase::class.java, "pebble_database"
            ).addTypeConverter(TodoListTypeConverter(gson)).build()
        }

    }

}