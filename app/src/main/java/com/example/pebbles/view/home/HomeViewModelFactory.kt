package com.example.pebbles.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pebbles.domain.usecase.GetHabitsFromAPIUseCase
import com.example.pebbles.domain.usecase.GetHabitsFromDBUseCase

class HomeViewModelFactory(
    private val getHabitsFromAPIUseCase: GetHabitsFromAPIUseCase,
    private val getHabitsFromDBUseCase: GetHabitsFromDBUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getHabitsFromAPIUseCase, getHabitsFromDBUseCase) as T
    }
}