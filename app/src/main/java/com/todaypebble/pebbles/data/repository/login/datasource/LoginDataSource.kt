package com.todaypebble.pebbles.data.repository.login.datasource

import com.todaypebble.pebbles.data.remote.dto.login.duplicate.DuplicateChkResponse
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginRequest
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginResponse
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpResponse
import retrofit2.Response

interface LoginDataSource {

    suspend fun signUp(signUpRequest: SignUpRequest) : Response<SignUpResponse>
    suspend fun login(loginRequest: LoginRequest) : Response<LoginResponse>
    suspend fun duplicateChk(username: String) : Response<DuplicateChkResponse>
}