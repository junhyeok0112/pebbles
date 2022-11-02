package com.example.pebbles.di.core

import android.content.Context
import androidx.room.Room
import com.example.pebbles.data.db.PEBBLEDataBase
import com.example.pebbles.data.model.HabitDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideHabitDataBase(@ApplicationContext context: Context) : PEBBLEDataBase{
        return Room.databaseBuilder(context, PEBBLEDataBase::class.java, "pebble-database").build()
    }

    @Provides
    fun provideHabitDao(pebbleDataBase: PEBBLEDataBase) : HabitDAO{
        return pebbleDataBase.habitDao()
    }
}