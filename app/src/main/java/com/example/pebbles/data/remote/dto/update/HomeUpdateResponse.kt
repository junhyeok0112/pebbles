package com.example.pebbles.data.remote.dto.update

import com.google.gson.annotations.SerializedName

data class HomeUpdateResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: String
)