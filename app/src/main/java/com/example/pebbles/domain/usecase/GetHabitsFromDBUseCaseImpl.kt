package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class GetHabitsFromDBUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : GetHabitsFromDBUseCase{

    override suspend operator fun invoke(): ArrayList<Habit>? = homeRepository.getHabitsFromDB()
}