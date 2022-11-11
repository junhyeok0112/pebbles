package com.todaypebble.pebbles.domain.usecase.manage

import android.util.Log
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.domain.repository.ManageRepository
import javax.inject.Inject

class GetMyStonesUseCaseImpl @Inject constructor(private val manageRepository: ManageRepository): GetMyStonesUseCase {
    override suspend fun invoke(userId: Int): ArrayList<MyStone> {
        Log.d("ManageViewModel", "getMyStones 실행5")
        return manageRepository.getMyStones(userId)
    }
}