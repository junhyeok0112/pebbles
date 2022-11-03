package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.model.Habit

interface UpdateTodoToDBUseCase {

    suspend operator fun invoke(habit: Habit)

}