package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class MakeStoneRequest(
    @SerializedName("end") val end: String,
    @SerializedName("habits") val habits: List<Habit>,
    @SerializedName("name") val name: String,
    @SerializedName("start") val start: String
)