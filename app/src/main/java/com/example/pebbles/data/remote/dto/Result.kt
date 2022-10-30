package com.example.pebbles.data.remote.dto

import com.example.pebbles.data.remote.model.Habit
import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("habits") val habits: ArrayList<Habit>,
    @SerializedName("today") val today: String
)