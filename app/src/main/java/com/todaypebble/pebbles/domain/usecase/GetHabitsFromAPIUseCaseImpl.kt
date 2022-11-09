package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.data.remote.model.Habit
import com.todaypebble.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class GetHabitsFromAPIUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : GetHabitsFromAPIUseCase {

    override suspend operator fun invoke(): ArrayList<Habit>? = homeRepository.getHabitsFromAPI()

}