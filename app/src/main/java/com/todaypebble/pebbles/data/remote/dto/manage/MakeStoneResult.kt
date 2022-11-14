package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class MakeStoneResult(
    @SerializedName("habits_name") val habits_name: List<String>,
    @SerializedName("highlight_name") val highlight_name: String,
    @SerializedName("todos_name") val todos_name: List<String>
)