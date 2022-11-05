package com.example.pebbles.domain.usecase.login

import com.example.pebbles.data.remote.dto.login.duplicate.DuplicateChkResponse
import com.example.pebbles.domain.repository.LogInRepository
import retrofit2.Response
import javax.inject.Inject

class DuplicateChkUseCaseImpl @Inject constructor(private val logInRepository: LogInRepository) : DuplicateChkUseCase{
    override suspend fun invoke(username: String): Response<DuplicateChkResponse> = logInRepository.duplicateChk(username)
}