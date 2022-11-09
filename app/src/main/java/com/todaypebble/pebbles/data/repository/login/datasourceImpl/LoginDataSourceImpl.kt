package com.todaypebble.pebbles.data.repository.login.datasourceImpl

import com.todaypebble.pebbles.data.remote.api.LoginService
import com.todaypebble.pebbles.data.remote.dto.login.duplicate.DuplicateChkResponse
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginRequest
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginResponse
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpResponse
import com.todaypebble.pebbles.data.repository.login.datasource.LoginDataSource
import retrofit2.Response
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(private val loginService: LoginService) : LoginDataSource {
    override suspend fun signUp(signUpRequest: SignUpRequest): Response<SignUpResponse> = loginService.signUp(signUpRequest)
    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> = loginService.login(loginRequest)
    override suspend fun duplicateChk(username: String): Response<DuplicateChkResponse> = loginService.duplicateChk(username)
}