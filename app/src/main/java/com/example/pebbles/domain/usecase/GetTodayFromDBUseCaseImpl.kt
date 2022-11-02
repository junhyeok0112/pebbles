package com.example.pebbles.domain.usecase

import com.example.pebbles.data.model.HabitDAO
import com.example.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class GetTodayFromDBUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : GetTodayFromDBUseCase {
    override suspend fun invoke(): String = homeRepository.getTodayFromDB()

}