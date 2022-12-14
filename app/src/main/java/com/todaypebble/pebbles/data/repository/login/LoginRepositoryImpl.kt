package com.todaypebble.pebbles.data.repository.login

import android.util.Log
import com.todaypebble.pebbles.data.remote.dto.login.duplicate.DuplicateChkResponse
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginRequest
import com.todaypebble.pebbles.data.remote.dto.login.login.LoginResponse
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.todaypebble.pebbles.data.remote.dto.login.signup.SignUpResponse
import com.todaypebble.pebbles.data.repository.login.datasource.LoginDataSource
import com.todaypebble.pebbles.domain.repository.LogInRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource : LoginDataSource
) : LogInRepository{

    override suspend fun signUp(signUpRequest: SignUpRequest): Response<SignUpResponse> {
        lateinit var signUpResponse : Response<SignUpResponse>
        try {
            signUpResponse = loginDataSource.signUp(signUpRequest)
        }catch(e: Exception){
            Log.d("LoginRepository_Exception" , e.message.toString())
        }
        return signUpResponse
    }

    override suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        lateinit var loginResponse : Response<LoginResponse>
        try {
            loginResponse = loginDataSource.login(loginRequest)
        }catch(e: Exception){
            Log.d("LoginRepository_Exception" , e.message.toString())
        }
        return loginResponse
    }

    override suspend fun duplicateChk(username: String): Response<DuplicateChkResponse> {
        lateinit var duplicateChkResponse: Response<DuplicateChkResponse>
        try {
            duplicateChkResponse = loginDataSource.duplicateChk(username)
        }catch(e: Exception){
            Log.d("LoginRepository_Exception" , e.message.toString())
        }
        return duplicateChkResponse
    }
}