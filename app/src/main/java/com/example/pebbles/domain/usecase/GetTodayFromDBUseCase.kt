package com.example.pebbles.domain.usecase

interface GetTodayFromDBUseCase {

    suspend operator fun invoke() : String

}