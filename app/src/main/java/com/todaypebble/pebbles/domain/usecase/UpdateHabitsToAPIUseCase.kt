package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateResponse
import retrofit2.Response

interface UpdateHabitsToAPIUseCase {

    suspend operator fun invoke(updateRequest: HomeUpdateRequest): Response<HomeUpdateResponse>

}