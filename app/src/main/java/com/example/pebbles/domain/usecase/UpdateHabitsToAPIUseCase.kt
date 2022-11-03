package com.example.pebbles.domain.usecase

import com.example.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.example.pebbles.data.remote.dto.update.HomeUpdateRequestItem
import com.example.pebbles.data.remote.dto.update.HomeUpdateResponse
import retrofit2.Response

interface UpdateHabitsToAPIUseCase {

    suspend operator fun invoke(updateRequest: HomeUpdateRequest): Response<HomeUpdateResponse>

}