package com.todaypebble.pebbles.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("id") val id : Int,           //todo 아이디
    @SerializedName("name") val name : String,      //Todo 이름
    @SerializedName("seq") val seq: Int,           //todo 순서
    @SerializedName("status") var status : String     //todo 전부 완료 여부
)
