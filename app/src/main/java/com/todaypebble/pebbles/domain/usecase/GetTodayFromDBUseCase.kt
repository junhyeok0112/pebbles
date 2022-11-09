package com.todaypebble.pebbles.domain.usecase

interface GetTodayFromDBUseCase {

    suspend operator fun invoke() : String

}