package com.example.pebbles.repository.home

import com.example.pebbles.network.RetrofitInstance
import com.example.pebbles.network.home.HomeInterface

class HomeRepository {

    val homeService = RetrofitInstance.getRetrofit.create(HomeInterface::class.java)

}