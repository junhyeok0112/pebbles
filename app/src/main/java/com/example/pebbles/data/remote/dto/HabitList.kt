package com.example.pebbles.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HabitList(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result
)