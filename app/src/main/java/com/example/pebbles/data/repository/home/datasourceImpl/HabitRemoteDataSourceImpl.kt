package com.example.pebbles.data.repository.home.datasourceImpl

import com.example.pebbles.data.remote.api.HomeService
import com.example.pebbles.data.remote.dto.HabitList
import com.example.pebbles.data.repository.home.datasource.HabitRemoteDataSource
import com.example.pebbles.util.getJwt
import com.example.pebbles.util.getUserIdx
import retrofit2.Response
import javax.inject.Inject

class HabitRemoteDataSourceImpl @Inject constructor(private val homeService: HomeService) : HabitRemoteDataSource {
    override suspend fun getHabits(): Response<HabitList>  = homeService.getHabits(getUserIdx() , getJwt())
}