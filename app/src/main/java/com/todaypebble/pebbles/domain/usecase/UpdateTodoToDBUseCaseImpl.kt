package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class UpdateTodoToDBUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : UpdateTodoToDBUseCase {
    override suspend fun invoke(habit: Habit) {
        homeRepository.updateTodoToDB(habit)
    }
}