package com.example.pebbles.di.core

import com.example.pebbles.domain.repository.HomeRepository
import com.example.pebbles.domain.usecase.GetHabitsFromAPIUseCase
import com.example.pebbles.domain.usecase.GetHabitsFromAPIUseCaseImpl
import com.example.pebbles.domain.usecase.GetHabitsFromDBUseCase
import com.example.pebbles.domain.usecase.GetHabitsFromDBUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//UseCase들을 매개변수로 넘기기 위해서 생성 - >즉 매개변수로 넘기는 것들을 전부 DI 생성한다 생각하자.

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    //내부 DB에서 사용하는 useCase
    @Singleton
    @Binds
    abstract fun provideGetHabitFromDBUseCase(getHabitsFromDBUseCaseImpl: GetHabitsFromDBUseCaseImpl) : GetHabitsFromDBUseCase


    //외부 Retrofit에서 사용하는 UseCase
    @Singleton
    @Binds
    abstract fun provideGetHabitsFromAPIUseCase(gtHabitsFromAPIUseCaseImpl: GetHabitsFromAPIUseCaseImpl) : GetHabitsFromAPIUseCase

}