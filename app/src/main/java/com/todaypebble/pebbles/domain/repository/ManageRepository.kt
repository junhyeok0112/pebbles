package com.todaypebble.pebbles.domain.repository

import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneRequest
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import retrofit2.Response

interface ManageRepository {
    suspend fun getMyStones(userId : Int) : ArrayList<MyStone>
    suspend fun postMakeStone(userId: Int, makeStoneRequest: MakeStoneRequest): MakeStoneResponse
}