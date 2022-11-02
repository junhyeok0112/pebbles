package com.example.pebbles.data.remote.api

import com.example.pebbles.data.remote.dto.HabitList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
Rest API 서버와 통신하는 방법을 정의한 인터페이스

Call같은 경우는 명시적으로 Success / Fail을 나눠서 처리
Response 같은 경우는 서버에서 Status Code를 받아서 케이스를 나눠 처리
Callback Hell을 방지하려면 Response를 이용해서 하는 것이 더 좋다고 한다.
 **/

interface HomeService {

    @GET("/api/home/{userId}")
    suspend fun getHabits(@Path("userId") userId:Int , @Header("x-access-token") jwt: String?) : Response<HabitList>

}