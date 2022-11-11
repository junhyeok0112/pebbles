package com.todaypebble.pebbles.domain.usecase.manage

import com.todaypebble.pebbles.data.remote.dto.manage.MyStone

interface GetMyStonesUseCase {

    suspend operator fun invoke(userId: Int) : ArrayList<MyStone>
}