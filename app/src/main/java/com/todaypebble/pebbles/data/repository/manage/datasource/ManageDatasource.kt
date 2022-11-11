package com.todaypebble.pebbles.data.repository.manage.datasource

import com.todaypebble.pebbles.data.remote.dto.manage.MyStoneResponse
import retrofit2.Response

interface ManageDatasource {

    suspend fun getMyStones(userId : Int) : Response<MyStoneResponse>

}