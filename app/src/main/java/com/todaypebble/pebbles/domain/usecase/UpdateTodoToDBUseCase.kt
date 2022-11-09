package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.data.remote.model.Habit

interface UpdateTodoToDBUseCase {

    suspend operator fun invoke(habit: Habit)

}