package com.example.pebbles.data.remote.dto.login.duplicate

data class DuplicateChkResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Boolean
)