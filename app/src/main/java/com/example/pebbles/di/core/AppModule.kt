package com.example.pebbles.di.core

import android.content.Context
import com.example.pebbles.di.home.HomeSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//Application Context 의존성 주입을 위한 모듈
@Module(subcomponents = [HomeSubComponent::class])
class AppModule(private val context:Context) {

    @Singleton
    @Provides
    fun provideApplicationContext() : Context{
        return context.applicationContext
    }

}