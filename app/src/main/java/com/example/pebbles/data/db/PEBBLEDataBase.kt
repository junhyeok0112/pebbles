package com.example.pebbles.data.db

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.data.remote.model.Habit

@Database(entities = [Habit::class], version = 1, exportSchema = false)
abstract class PEBBLEDataBase : RoomDatabase() {

    abstract fun habitDao(): HabitDAO

}