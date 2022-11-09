package com.todaypebble.pebbles.di.core

import android.content.Context
import com.todaypebble.pebbles.data.db.PEBBLEDataBase
import com.todaypebble.pebbles.data.model.HabitDAO
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
    fun provideHabitDataBase(@ApplicationContext context: Context) : PEBBLEDataBase {
        return PEBBLEDataBase.getInstance(context)
    }

    @Provides
    fun provideHabitDao(pebbleDataBase: PEBBLEDataBase) : HabitDAO{
        return pebbleDataBase.habitDao()
    }
}