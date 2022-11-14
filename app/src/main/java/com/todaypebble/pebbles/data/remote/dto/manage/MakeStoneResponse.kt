package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class MakeStoneResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("makeStoneResult") val makeStoneResult: MakeStoneResult
)