package com.todaypebble.pebbles.domain.usecase.login

import com.todaypebble.pebbles.data.remote.dto.login.duplicate.DuplicateChkResponse
import retrofit2.Response

interface DuplicateChkUseCase {
    suspend operator fun invoke(username : String) : Response<DuplicateChkResponse>
}