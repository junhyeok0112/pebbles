package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository

class GetHabitsFromDBUseCase(private val homeRepository: HomeRepository) {

    suspend fun execute() : ArrayList<Habit>? = homeRepository.getHabitsFromDB()

}