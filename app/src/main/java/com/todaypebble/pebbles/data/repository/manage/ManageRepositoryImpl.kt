package com.todaypebble.pebbles.data.repository.manage

import android.util.Log
import com.todaypebble.pebbles.MyApplicationClass.Companion.userId
import com.todaypebble.pebbles.data.remote.dto.manage.DetailMyStoneResult
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneRequest
import com.todaypebble.pebbles.data.remote.dto.manage.MakeStoneResponse
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.data.repository.manage.datasource.ManageDatasource
import com.todaypebble.pebbles.domain.repository.ManageRepository
import retrofit2.Response
import javax.inject.Inject

class ManageRepositoryImpl @Inject constructor(
    private val manageDatasource : ManageDatasource
) : ManageRepository {


    override suspend fun getMyStones(userId: Int): ArrayList<MyStone> {
        //바윗돌 리스트 가져오기
        Log.d("ManageViewModel", "getMyStones 실행6")
        lateinit var stoneList : ArrayList<MyStone>
        try{
            //Service 통해서 가져오기 -> 추후에 userId, JWT 토큰 , Datasource단에서 매개변수 처리됨
            val response = manageDatasource.getMyStones(userId)
            val body = response.body()
            Log.d("ManageRepository" , "레포 실행 : ${response.code()} 코드 , ${response.body()} 바디")
            if(body != null){
                //넘어온 List 셋팅
                stoneList = body.result as ArrayList<MyStone>
                Log.d("ManageRepository- TEST" , "넘어온 List ${stoneList}")
            }
        }catch(e: Exception){
            Log.d("ManageRepository_Exception" , e.message.toString())
        }

        return stoneList
    }

    //바윗돌 생성하기
    override suspend fun postMakeStone(
        userId: Int,
        makeStoneRequest: MakeStoneRequest
    ): MakeStoneResponse {
        Log.d("postMakeStone" , "실행")
        lateinit var body : MakeStoneResponse
        try{
            val response = manageDatasource.postMakeStone(userId, makeStoneRequest)
            if(response.body() != null){
                body = response.body()!!
            }

        } catch(e: Exception){
           Log.d("ManageRepository_Exception " ,e.message.toString())
         }

        return body
    }

    //바잇돌 세부 정보 가져오기
    override suspend fun getDetailMyStone(userId: Int, highligh_id: Int): DetailMyStoneResult {
        Log.d("getDetailMyStone" , "실행")
        lateinit var body : DetailMyStoneResult
        try{
            Log.d("getDetailMyStone" , "${userId} , ${highligh_id}")
            val response = manageDatasource.getDetailMyStone(userId, highligh_id)
            Log.d("getDetailMyStone" , "${response}")
            if(response.body() != null){
                body = response.body()!!.result
            }
            //원래라면 잘 못 가져왔을 때 처리해야함
        } catch(e: Exception){
            Log.d("ManageRepository_Exception " ,e.message.toString())
        }

        return body
    }
}