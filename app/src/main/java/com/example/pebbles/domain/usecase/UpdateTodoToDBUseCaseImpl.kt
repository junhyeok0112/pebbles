package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class UpdateTodoToDBUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : UpdateTodoToDBUseCase {
    override suspend fun invoke(habit: Habit) {
        homeRepository.updateTodoToDB(habit)
    }
}