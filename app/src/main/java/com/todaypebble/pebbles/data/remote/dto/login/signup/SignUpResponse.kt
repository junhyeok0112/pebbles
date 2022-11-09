package com.todaypebble.pebbles.data.remote.dto.login.signup

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: SignUpResult
)