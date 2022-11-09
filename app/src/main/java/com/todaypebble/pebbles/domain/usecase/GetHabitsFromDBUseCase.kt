package com.todaypebble.pebbles.domain.usecase

import com.todaypebble.pebbles.data.remote.model.Habit

interface GetHabitsFromDBUseCase {

    suspend operator fun invoke() : List<Habit>?

}