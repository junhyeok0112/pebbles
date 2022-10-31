package com.example.pebbles.di.core

import com.example.pebbles.data.remote.api.HomeService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl: String) {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit) : HomeService{
        return retrofit.create(HomeService::class.java)
    }
}