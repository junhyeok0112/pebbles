package com.example.pebbles.view.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pebbles.data.remote.dto.login.login.LoginRequest
import com.example.pebbles.data.remote.dto.login.login.LoginResponse
import com.example.pebbles.data.remote.dto.login.signup.SignUpRequest
import com.example.pebbles.data.remote.dto.login.signup.SignUpResponse
import com.example.pebbles.domain.usecase.login.DuplicateChkUseCase
import com.example.pebbles.domain.usecase.login.LoginUseCase
import com.example.pebbles.domain.usecase.login.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

//AndroidViewModel 상속시 생성자 파라미터에 값 넣어주어야함 →context 등을 받아야할 경우 AndroidViewModel을 사용합니다.
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val duplicateChkUseCase : DuplicateChkUseCase
) : ViewModel() {

    var nickname = MutableLiveData<String>().apply { value = "" }

    //패스워드 입력
    var password = MutableLiveData<String>().apply { value = "" }

    //패스워드 체크 입력
    var password_chk = MutableLiveData<String>().apply { value = "" }

    //7글자 이내 체크 -> 3가지 상태
    var inSevenChk = MutableLiveData<Boolean>().apply { value = false }

    //특수문자 사용불가  -> 3가지 상태
    var cannotSpecial = MutableLiveData<Boolean>().apply { value = false }

    //중복체크 활성화
    var isduplicateChk = MutableLiveData<Boolean>().apply { value = false }

    //사용 가능 닉네임 -> DataBinding 체크
    var canUseNick = MutableLiveData<Boolean>().apply { value = false }

    //8~16글자 이내 -> 3가지 상태
    var pwInEight = MutableLiveData<Boolean>().apply { value = false }

    //영문,숫자,특수문자 중 2가지 이상 -> 3가지 상태
    var engNumSpecialTwo = MutableLiveData<Boolean>().apply { value = false }

    //비밀번호 일치 -> DataBinding 체크
    var pwCoincide = MutableLiveData<Boolean>().apply { value = false }

    var isClickBtn = MutableLiveData<Boolean>().apply { value = false }

    var _content = MutableLiveData<String>().apply { value = "" }
    val content: LiveData<String>
        get() = _content

    //로그인 화면 닉네임
    var login_id = MutableLiveData<String>().apply { value ="" }
    //로그인 화면 패스워드
    var login_pw = MutableLiveData<String>().apply { value = "" }

    //로그인 가능한지
    var canLogin = MutableLiveData<Boolean>().apply { value = false }

    //버튼 클릭 가능하게 바꾸는 변수
    fun changeBtn() {
        if(inSevenChk.value!! && cannotSpecial.value!! && canUseNick.value!! && pwInEight.value!! &&  engNumSpecialTwo.value!! && pwCoincide.value!! ){
            isClickBtn.value = true
        } else{
            isClickBtn.value = false
        }
    }

    //회원가입
    suspend fun signUp() : Response<SignUpResponse> {
        val signUpRequest = SignUpRequest(null , password.value!! ,nickname.value!!)
        val signUpResponse = signUpUseCase(signUpRequest)
        return signUpResponse
    }

    //로그인
    suspend fun logIn() : Response<LoginResponse>{
        val logInRequest = LoginRequest(login_pw.value!! , login_id.value!!)
        val loginResponse = loginUseCase(logInRequest)
        return loginResponse
    }

    //회원가입 데이터 초기화
    fun initSignUpData(){
        nickname.value = ""
        password.value =""
        password_chk.value =""
    }

    //로그인 데이터 초기화
    fun initLoginData(){
        login_id.value =""
        login_pw.value= ""
    }

    //중복 검사하는 함수 호출 -> 중복인지 True면 중복 , False면 중복 아님
    suspend fun duplicateChk() : Boolean? {
        val duplicateChkResponse =  duplicateChkUseCase(nickname.value!!)
        Log.d("Duplicate_Test" , "${duplicateChkResponse}")
        Log.d("Duplicate_Test" , "${duplicateChkResponse.body()}")
        return duplicateChkUseCase(nickname.value!!).body()?.result
    }


}