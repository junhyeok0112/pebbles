package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.model.Habit
import com.example.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class GetHabitsFromAPIUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : GetHabitsFromAPIUseCase {

    override suspend operator fun invoke(): ArrayList<Habit>? = homeRepository.getHabitsFromAPI()

}