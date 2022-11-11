package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class MyStone(
    @SerializedName("id")val id: Int,
    @SerializedName("name")val name: String,
    @SerializedName("start")val start: String,
    @SerializedName("end") val end : String
)