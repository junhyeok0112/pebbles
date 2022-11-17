package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class DetailMyStoneHabitRes(
    @SerializedName("end") val end: String,
    @SerializedName("getRockManageDetailTodoResList") val getRockManageDetailTodoResList: ArrayList<DetailMyStoneTodoRes>,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("seq") val seq: Int,
    @SerializedName("start") val start: String,
    @SerializedName("weeks") val weeks: Weeks
)