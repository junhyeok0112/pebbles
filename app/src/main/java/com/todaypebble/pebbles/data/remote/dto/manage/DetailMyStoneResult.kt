package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class DetailMyStoneResult(
    @SerializedName("end") val end: String ="",
    @SerializedName("getRockManageDetailHabitResList") val getRockManageDetailHabitResList: ArrayList<DetailMyStoneHabitRes> = ArrayList(),
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String ="",
    @SerializedName("start") val start: String = ""
)