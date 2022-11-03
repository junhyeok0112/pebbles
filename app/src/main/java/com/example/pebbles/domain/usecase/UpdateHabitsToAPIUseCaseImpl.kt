package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.example.pebbles.data.remote.dto.update.HomeUpdateRequestItem
import com.example.pebbles.data.remote.dto.update.HomeUpdateResponse
import com.example.pebbles.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class UpdateHabitsToAPIUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository): UpdateHabitsToAPIUseCase {
    override suspend fun invoke(updateRequest: HomeUpdateRequest) : Response<HomeUpdateResponse> = homeRepository.updateHabitToAPI(updateRequest)

}