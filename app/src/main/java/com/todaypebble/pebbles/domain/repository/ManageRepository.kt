package com.todaypebble.pebbles.domain.repository

import com.todaypebble.pebbles.data.remote.dto.manage.MyStone

interface ManageRepository {
    suspend fun getMyStones(userId : Int) : ArrayList<MyStone>
}