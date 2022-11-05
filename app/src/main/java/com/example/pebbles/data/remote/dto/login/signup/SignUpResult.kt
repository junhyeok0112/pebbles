package com.example.pebbles.data.remote.dto.login.signup

import com.google.gson.annotations.SerializedName

data class SignUpResult(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userId") val userId: Int
)