package com.todaypebble.pebbles.data.remote.api

import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneRequest
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MyStoneResponse
import retrofit2.Response
import retrofit2.http.*

interface ManageService {

    @GET("api/rock/manage/{userId}")
    suspend fun getMyStones( @Header("x-access-token") jwt: String? ,@Path("userId") userId:Int) : Response<MyStoneResponse>

    @POST("api/rock/manage/new/{userId}")
    suspend fun postMakeStone(@Header("x-access-token") jwt: String? , @Path("userId") userId: Int , @Body makeStoneRequest: MakeStoneRequest) :Response<MakeStoneResponse>

    @GET("api/rock/manage/{userId}/{highlight_id}")
    suspend fun getDetailMyStone(@Header("x-access-token") jwt: String? ,@Path("userId") userId:Int , @Path("highlight_id") highlight_id: Int) : Response<DetailMyStoneResponse>
}