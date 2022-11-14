package com.todaypebble.pebbles.domain.usecase.manage

import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneRequest
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneResponse
import com.todaypebble.pebbles.domain.repository.ManageRepository
import retrofit2.Response
import javax.inject.Inject

class PostMakeStoneUseCaseImpl @Inject constructor(private val manageRepository: ManageRepository) : PostMakeStoneUseCase{
    override suspend fun invoke(
        userId: Int,
        makeStoneRequest: MakeStoneRequest
    ): MakeStoneResponse = manageRepository.postMakeStone(userId, makeStoneRequest)
}