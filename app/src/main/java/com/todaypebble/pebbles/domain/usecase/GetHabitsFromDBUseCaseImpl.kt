package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class GetHabitsFromDBUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : GetHabitsFromDBUseCase{

    override suspend operator fun invoke(): List<Habit>? = homeRepository.getHabitsFromDB()
}