package com.todaypebble.pebbles.domain.usecase.manage

import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneResult
import com.todaypebble.pebbles.domain.repository.ManageRepository
import javax.inject.Inject

class GetDetailMyStoneUseCaseImpl @Inject constructor(private val manageRepository: ManageRepository) : GetDetailMyStoneUseCase {

    override suspend fun invoke(userId: Int, highlight_id: Int) : DetailMyStoneResult = manageRepository.getDetailMyStone(userId , highlight_id)
}