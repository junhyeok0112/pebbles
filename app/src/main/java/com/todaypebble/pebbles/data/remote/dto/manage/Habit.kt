package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class Habit(
    @SerializedName("days") var days: ArrayList<String>, //요일에 해당하는 일자
    @SerializedName("end") var end: String,
    @SerializedName("name") var name: String,
    @SerializedName("seq") var seq: Int,
    @SerializedName("start") var start: String,
    @SerializedName("todos") var todos: ArrayList<Todo>,
    @SerializedName("weeks") var weeks: Weeks
)