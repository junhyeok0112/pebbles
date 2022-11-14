package com.todaypebble.pebbles.domain.usecase.manage

import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneRequest
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneResponse
import retrofit2.Response

interface PostMakeStoneUseCase {

    //유저 아아디랑 , Request 값 , 리턴값으로 ... 그냥 Response 값 전부 받자
    suspend operator fun invoke(userId : Int , makeStoneRequest: MakeStoneRequest) : MakeStoneResponse
}