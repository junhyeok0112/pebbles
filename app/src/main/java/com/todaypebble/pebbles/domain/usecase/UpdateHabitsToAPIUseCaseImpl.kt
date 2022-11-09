package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateResponse
import com.todaypebble.pebbles.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class UpdateHabitsToAPIUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository): UpdateHabitsToAPIUseCase {
    override suspend fun invoke(updateRequest: HomeUpdateRequest) : Response<HomeUpdateResponse> = homeRepository.updateHabitToAPI(updateRequest)

}