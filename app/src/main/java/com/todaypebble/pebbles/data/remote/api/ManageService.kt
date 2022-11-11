package com.todaypebble.pebbles.data.remote.api

import com.todaypebble.pebbles.data.remote.dto.manage.MyStoneResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ManageService {

    @GET("api/rock/manage/{userId}")
    suspend fun getMyStones( @Header("x-access-token") jwt: String? ,@Path("userId") userId:Int) : Response<MyStoneResponse>

}