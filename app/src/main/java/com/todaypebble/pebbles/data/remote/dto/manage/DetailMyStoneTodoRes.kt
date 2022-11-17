package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class DetailMyStoneTodoRes(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("seq") val seq: Int
)