package com.todaypebble.pebbles.di.core

import com.todaypebble.pebbles.data.repository.home.datasource.HabitLocalDataSource
import com.todaypebble.pebbles.data.repository.home.datasource.HabitRemoteDataSource
import com.todaypebble.pebbles.data.repository.home.datasourceImpl.HabitLocalDataSourceImpl
import com.todaypebble.pebbles.data.repository.home.datasourceImpl.HabitRemoteDataSourceImpl
import com.todaypebble.pebbles.data.repository.login.datasource.LoginDataSource
import com.todaypebble.pebbles.data.repository.login.datasourceImpl.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    //HabitLocalDataSource 는 인터페이스이고 implmented된다 Impl에 의해서 따라서 Impl을 return 해준다

    //RoomDB 관련 DataSource
    @Singleton
    @Binds
    abstract fun provideHabitLocalDataSource(localDataSourceImpl: HabitLocalDataSourceImpl) : HabitLocalDataSource

    //원격 Remote 관련 DataSource
    @Singleton
    @Binds
    abstract fun provideHabitRemoteDataSource(remoteDataSourceImpl: HabitRemoteDataSourceImpl) : HabitRemoteDataSource

    //로그인 DataSource
    @Singleton
    @Binds
    abstract fun provideLoginDataSource(loginDataSourceImpl: LoginDataSourceImpl) : LoginDataSource
}