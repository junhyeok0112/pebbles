package com.todaypebble.pebbles.domain.usecase.login

import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpResponse
import retrofit2.Response

interface SignUpUseCase {

    suspend operator fun invoke(signUpRequest: SignUpRequest) : Response<SignUpResponse>

}