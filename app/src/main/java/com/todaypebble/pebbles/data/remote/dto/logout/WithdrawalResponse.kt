package com.todaypebble.pebbles.data.remote.dto.logout

data class WithdrawalResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: String
)