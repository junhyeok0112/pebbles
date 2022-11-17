package com.todaypebble.pebbles.domain.usecase.manage

import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneResult

interface GetDetailMyStoneUseCase {

    suspend operator fun invoke(userId : Int , highlight_id :Int) : DetailMyStoneResult

}