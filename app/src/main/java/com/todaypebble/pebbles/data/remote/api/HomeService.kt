package com.todaypebble.pebbles.data.remote.api

import com.todaypebble.pebbles.data.remote.dto.HabitList
import com.todaypebble.pebbles.data.remote.dto.logout.WithdrawalResponse
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateResponse
import retrofit2.Response
import retrofit2.http.*

/**
Rest API 서버와 통신하는 방법을 정의한 인터페이스

Call같은 경우는 명시적으로 Success / Fail을 나눠서 처리
Response 같은 경우는 서버에서 Status Code를 받아서 케이스를 나눠 처리
Callback Hell을 방지하려면 Response를 이용해서 하는 것이 더 좋다고 한다.
 **/

interface HomeService {

    @GET("/api/home/{userId}")
    suspend fun getHabits( @Header("x-access-token") jwt: String? ,@Path("userId") userId:Int) : Response<HabitList>

    //해빗의 today_status 갱신
    @POST("/api/home/{userId}/update")
    suspend fun updateHabits( @Header("x-access-token") jwt: String?, @Path("userId") userId:Int, @Body homeUpdateRequest: HomeUpdateRequest) : Response<HomeUpdateResponse>

    //유저 회원 탈퇴 -> MainActivity위에서 이루어지는 작업이므로 여기서 작성
    @POST("api/user/{userId}/out")
    suspend fun withdrawal(@Header("x-access-token") jwt: String?, @Path("userId") userId: Int) : Response<WithdrawalResponse>
}