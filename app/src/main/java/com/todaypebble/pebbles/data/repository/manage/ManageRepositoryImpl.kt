package com.todaypebble.pebbles.data.repository.manage

import android.util.Log
import com.todaypebble.pebbles.data.remote.dto.manage.MyStone
import com.todaypebble.pebbles.data.repository.manage.datasource.ManageDatasource
import com.todaypebble.pebbles.domain.repository.ManageRepository
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

}