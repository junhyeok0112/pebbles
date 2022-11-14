package com.todaypebble.pebbles.data.remote.dto.manage

import com.google.gson.annotations.SerializedName

data class Weeks(
    @SerializedName("mon") var mon: Boolean,
    @SerializedName("tue") var tue: Boolean,
    @SerializedName("wed") var wed: Boolean,
    @SerializedName("thu") var thu: Boolean,
    @SerializedName("fri") var fri: Boolean,
    @SerializedName("sat") var sat: Boolean,
    @SerializedName("sun") var sun: Boolean,

    )