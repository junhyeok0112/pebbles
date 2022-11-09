package com.todaypebble.pebbles.data.remote.dto.login.signup

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("goal") val goal: String?,
    @SerializedName("password") val password: String,
    @SerializedName("username") val username: String
)