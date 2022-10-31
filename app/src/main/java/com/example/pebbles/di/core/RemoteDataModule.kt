package com.example.pebbles.di.core

import com.example.pebbles.data.remote.api.HomeService
import com.example.pebbles.data.repository.home.datasource.HabitRemoteDataSource
import com.example.pebbles.data.repository.home.datasourceImpl.HabitRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

//DataSource를 위한 모듈 생성 -> 이걸 Impl 인스턴스에 제공

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideHabitRemoteDataSource(homeService: HomeService) : HabitRemoteDataSource{
        return HabitRemoteDataSourceImpl(homeService)
    }
}