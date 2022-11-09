package com.todaypebble.pebbles.data.remote.dto.login.login

data class LoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: LoginResult
)