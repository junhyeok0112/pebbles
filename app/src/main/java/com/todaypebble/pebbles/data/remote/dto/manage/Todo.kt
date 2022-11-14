package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("name") var name: String,
    @SerializedName("seq") var seq: Int
)