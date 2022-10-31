package com.example.pebbles.data.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.data.remote.model.Habit
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Habit::class], version = 1, exportSchema = false)
abstract class PEBBLEDataBase : RoomDatabase() {

    abstract fun habitDao(): HabitDAO

    //DB를 싱글톤으로 만들어서 메모리 관리해야함 -> 이 때 Application에 따로 할필요 없이 여기서 Companion object로 처리 -> 공식문서
    //이 DB.DAO로 접근 가능
    companion object{
        @Volatile
        private var INSTANCE : PEBBLEDataBase? = null

        fun getDB(context : Context) : PEBBLEDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PEBBLEDataBase::class.java,
                    "pebble_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}