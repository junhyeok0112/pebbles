package com.example.pebbles.data.remote.api

import com.example.pebbles.data.remote.dto.login.login.LoginRequest
import com.example.pebbles.data.remote.dto.login.login.LoginResponse
import com.example.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.example.pebbles.data.remote.dto.login.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/api/join")
    suspend fun signUp(@Body signUpRequest: SignUpRequest) : Response<SignUpResponse>

    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>

}