package com.todaypebble.pebbles.data.remote.api

import com.todaypebble.pebbles.data.remote.dto.login.duplicate.DuplicateChkResponse
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginRequest
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginResponse
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("/api/join")
    suspend fun signUp(@Body signUpRequest: SignUpRequest) : Response<SignUpResponse>

    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<LoginResponse>

    @POST("/api/duplicate/username")
    suspend fun duplicateChk(@Query("username") username: String): Response<DuplicateChkResponse>

}