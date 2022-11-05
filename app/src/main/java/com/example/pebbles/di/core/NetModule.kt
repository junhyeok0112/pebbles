package com.example.pebbles.di.core

import com.example.pebbles.data.remote.api.HomeService
import com.example.pebbles.data.remote.api.LoginService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://umc-kyj.shop/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit) : HomeService{
        return retrofit.create(HomeService::class.java)
    }

    //로그인 관련 Service
    @Singleton
    @Provides
    fun provideLoginService(retrofit : Retrofit) : LoginService{
        return retrofit.create(LoginService::class.java)
    }
}