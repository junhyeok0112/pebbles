package com.todaypebble.pebbles.data.repository.home.datasourceImpl

import com.todaypebble.pebbles.data.remote.api.HomeService
import com.todaypebble.pebbles.data.remote.dto.HabitList
import com.todaypebble.pebbles.data.remote.dto.logout.WithdrawalResponse
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateRequest
import com.todaypebble.pebbles.data.remote.dto.update.HomeUpdateResponse
import com.todaypebble.pebbles.data.repository.home.datasource.HabitRemoteDataSource
import com.todaypebble.pebbles.util.getJwt
import com.todaypebble.pebbles.util.getUserIdx
import retrofit2.Response
import javax.inject.Inject

class HabitRemoteDataSourceImpl @Inject constructor(private val homeService: HomeService) : HabitRemoteDataSource {
    override suspend fun getHabits(): Response<HabitList>  = homeService.getHabits( getJwt() ,getUserIdx())
    override suspend fun updateHabits(updateRequest: HomeUpdateRequest) : Response<HomeUpdateResponse> = homeService.updateHabits(
        getJwt(), getUserIdx(), updateRequest)

    override suspend fun withdrawal(userId: Int): Response<WithdrawalResponse> = homeService.withdrawal(getJwt() , userId)
}