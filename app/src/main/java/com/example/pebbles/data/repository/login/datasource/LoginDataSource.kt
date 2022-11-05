package com.example.pebbles.data.repository.login.datasource

import com.example.pebbles.data.remote.dto.login.login.LoginRequest
import com.example.pebbles.data.remote.dto.login.login.LoginResponse
import com.example.pebbles.data.remote.dto.login.login.LoginResult
import com.example.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.example.pebbles.data.remote.dto.login.signup.SignUpResponse
import retrofit2.Response

interface LoginDataSource {

    suspend fun signUp(signUpRequest: SignUpRequest) : Response<SignUpResponse>
    suspend fun login(loginRequest: LoginRequest) : Response<LoginResponse>
}