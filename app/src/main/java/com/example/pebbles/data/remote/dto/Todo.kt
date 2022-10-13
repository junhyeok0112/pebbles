package com.example.pebbles.data.remote.dto

data class Todo(
    val id : Int,           //todo 아이디
    val name : String,      //Todo 이름
    val seq: Int,           //todo 순서
    var status : String     //todo 전부 완료 여부
)
