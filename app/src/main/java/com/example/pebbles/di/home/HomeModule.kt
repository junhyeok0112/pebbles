package com.example.pebbles.di.home

import com.example.pebbles.domain.usecase.GetHabitsFromAPIUseCase
import com.example.pebbles.domain.usecase.GetHabitsFromDBUseCase
import com.example.pebbles.view.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(getHabitsFromAPIUseCase: GetHabitsFromAPIUseCase , getHabitsFromDBUseCase: GetHabitsFromDBUseCase) : HomeViewModelFactory{
        return HomeViewModelFactory(getHabitsFromAPIUseCase , getHabitsFromDBUseCase)
    }
}