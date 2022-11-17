package com.todaypebble.pebbles.data.repository.manage.datasource

import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneRequest
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MyStoneResponse
import retrofit2.Response

interface ManageDatasource {

    suspend fun getMyStones(userId : Int) : Response<MyStoneResponse>
    suspend fun postMakeStone(userId: Int, makeStoneRequest: MakeStoneRequest): Response<MakeStoneResponse>
    suspend fun getDetailMyStone(userId: Int, highlighId: Int): Response<DetailMyStoneResponse>

}