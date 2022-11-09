package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.domain.repository.HomeRepository
import javax.inject.Inject

class GetTodayFromDBUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : GetTodayFromDBUseCase {
    override suspend fun invoke(): String = homeRepository.getTodayFromDB()

}