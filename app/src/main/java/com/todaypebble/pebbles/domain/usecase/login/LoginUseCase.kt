package com.todaypebble.pebbles.domain.usecase.login

import com.todaypebble.pebbles.data.remote.dto.login.login.LoginRequest
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginResponse
import retrofit2.Response

interface LoginUseCase {
    suspend operator fun invoke(loginRequest: LoginRequest) : Response<LoginResponse>

}