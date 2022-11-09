package com.todaypebble.pebbles.domain.usecase.login

import com.todaypebble.pebbles.data.remote.dto.login.login.LoginRequest
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginResponse
import com.todaypebble.pebbles.domain.repository.LogInRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val logInRepository: LogInRepository):LoginUseCase {
    override suspend fun invoke(loginRequest: LoginRequest): Response<LoginResponse> = logInRepository.login(loginRequest)
}