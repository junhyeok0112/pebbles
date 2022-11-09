package com.example.pebbles.domain.usecase.logout

import com.example.pebbles.data.remote.dto.logout.WithdrawalResponse
import retrofit2.Response

interface WithdrawalUseCase {

    suspend operator fun invoke(userId: Int) : Response<WithdrawalResponse>
}