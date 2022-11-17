package com.todaypebble.pebbles.data.repository.manage.datasourceImpl

import android.util.Log
import com.todaypebble.pebbles.data.remote.api.ManageService
import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneRequest
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MyStoneResponse
import com.todaypebble.pebbles.data.repository.manage.datasource.ManageDatasource
import com.todaypebble.pebbles.util.getJwt
import retrofit2.Response
import javax.inject.Inject

class ManageDatasourceImpl @Inject constructor(private val manageService: ManageService) :
    ManageDatasource {

    override suspend fun getMyStones(userId: Int): Response<MyStoneResponse>{
        Log.d("ManageViewModel", "getMyStones 실행7")
        Log.d("ManageViewModel" , "${getJwt()} + 유저아이디 ${userId} ")
        val temp  = manageService.getMyStones(getJwt() , userId)

        return temp
    }

    override suspend fun postMakeStone(
        userId: Int,
        makeStoneRequest: MakeStoneRequest
    ): Response<MakeStoneResponse> = manageService.postMakeStone(getJwt() , userId, makeStoneRequest)

    override suspend fun getDetailMyStone(
        userId: Int,
        highlighId: Int
    ): Response<DetailMyStoneResponse> = manageService.getDetailMyStone(getJwt(), userId , highlighId)
}