package com.todaypebble.pebbles.domain.usecase.logout

import com.todaypebble.pebbles.data.remote.dto.logout.WithdrawalResponse
import com.todaypebble.pebbles.domain.repository.HomeRepository
import retrofit2.Response
import javax.inject.Inject

class WithdrawalUseCaseImpl @Inject constructor(private val homeRepository: HomeRepository) : WithdrawalUseCase{
    override suspend fun invoke(userId: Int): Response<WithdrawalResponse> = homeRepository.withdrawal(userId)
}