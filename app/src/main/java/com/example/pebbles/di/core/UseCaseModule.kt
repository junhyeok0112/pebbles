package com.example.pebbles.di.core

import com.example.pebbles.domain.repository.HomeRepository
import com.example.pebbles.domain.usecase.GetHabitsFromAPIUseCase
import com.example.pebbles.domain.usecase.GetHabitsFromDBUseCase
import dagger.Module
import dagger.Provides

//UseCase들을 매개변수로 넘기기 위해서 생성 - >즉 매개변수로 넘기는 것들을 전부 DI 생성한다 생각하자.

@Module
class UseCaseModule {

    @Provides
    fun provideGetHabitFromDBUseCase(homeRepository: HomeRepository) : GetHabitsFromDBUseCase{
        return GetHabitsFromDBUseCase(homeRepository)
    }

    @Provides
    fun provideGetHabitsFromAPIUseCase(homeRepository: HomeRepository) : GetHabitsFromAPIUseCase{
        return GetHabitsFromAPIUseCase(homeRepository)
    }

}