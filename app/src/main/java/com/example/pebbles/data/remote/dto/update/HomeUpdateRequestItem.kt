package com.example.pebbles.data.remote.dto.update

import com.google.gson.annotations.SerializedName

data class HomeUpdateRequestItem(
    @SerializedName("id") val id: Int,
    @SerializedName("status") val status: String,
    @SerializedName("today") val today: String
)