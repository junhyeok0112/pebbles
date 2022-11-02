package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository

interface GetHabitsFromDBUseCase {

    suspend operator fun invoke() : List<Habit>?

}