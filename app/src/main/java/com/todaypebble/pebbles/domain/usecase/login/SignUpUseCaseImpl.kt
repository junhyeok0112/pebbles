package com.todaypebble.pebbles.domain.usecase.login

import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpResponse
import com.todaypebble.pebbles.domain.repository.LogInRepository
import retrofit2.Response
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(private val logInRepository: LogInRepository) : SignUpUseCase {

    override suspend operator fun invoke(signUpRequest: SignUpRequest) : Response<SignUpResponse> = logInRepository.signUp(signUpRequest)

}