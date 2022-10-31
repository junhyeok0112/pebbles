package com.example.pebbles.di.core

import android.content.Context
import androidx.room.Room
import com.example.pebbles.data.db.PEBBLEDataBase
import com.example.pebbles.data.model.HabitDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideHabitDataBase(context: Context) : PEBBLEDataBase{
        return Room.databaseBuilder(context, PEBBLEDataBase::class.java, "pebbleclient").build()
    }

    @Singleton
    @Provides
    fun provideHabitDao(pebbleDataBase: PEBBLEDataBase) : HabitDAO{
        return pebbleDataBase.habitDao()
    }
}