package com.example.pebbles.di.core

import com.example.pebbles.data.repository.home.HomeRepositoryImpl
import com.example.pebbles.data.repository.home.datasource.HabitLocalDataSource
import com.example.pebbles.data.repository.home.datasource.HabitRemoteDataSource
import com.example.pebbles.data.repository.login.LoginRepositoryImpl
import com.example.pebbles.domain.repository.HomeRepository
import com.example.pebbles.domain.repository.LogInRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//UseCase들이 Repository를 매개변수로 받기 때문에 이러한 Repository 의존정을 제공해야합니다.
//따라서 RepositoryModule을 만듭니다.

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    //이미 DataSource 모듈을 만들어놨기 때문에 이렇게 직접적으로 사용가능

    @Singleton
    @Binds
    abstract fun provideHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ) : HomeRepository


    @Singleton
    @Binds
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl) : LogInRepository
}