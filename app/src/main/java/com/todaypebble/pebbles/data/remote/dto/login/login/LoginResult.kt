package com.todaypebble.pebbles.data.remote.dto.login.login

data class LoginResult(
    val jwt: String,
    val userId: Int
)