package com.todaypebble.pebbles.view.login.fragment

import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentLoginSubBinding
import com.todaypebble.pebbles.view.MainActivity
import com.todaypebble.pebbles.view.login.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginSubFragment : BaseFragment<FragmentLoginSubBinding>(R.layout.fragment_login_sub) {

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun initAfterBinding() {
        binding.vm = loginViewModel
        binding.lifecycleOwner = this
        loginViewModel.initLoginData()
        setObserver()
        setListener()
    }

    fun setObserver() {
        loginViewModel.login_id.observe(this, {
            if (it.length != 0 && loginViewModel.login_pw.value!!.length != 0) {    //둘다 값이 있으면 true
                loginViewModel.canLogin.value = true
            }
        })

        loginViewModel.login_pw.observe(this, {
            if (it.length != 0 && loginViewModel.login_pw.value!!.length != 0) {    //둘다 값이 있으면 true
                loginViewModel.canLogin.value = true
            }
        })
    }

    fun setListener() {
        binding.loginBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val loginResponse = loginViewModel.logIn()
                Log.d("Login_Test", "${loginResponse}")

                withContext(Dispatchers.Main) {
                    if (loginResponse.body() != null) {
                        if (loginResponse.body()!!.isSuccess) {
                            showToast("로그인 성공")
                            Log.d("Login_test", "${loginResponse.body()}")
                            startActivityWithClear(MainActivity::class.java)
                        } else {
                            showToast(loginResponse.body()!!.message)
                            Log.d("Login_test", "${loginResponse.body()}")
                        }
                    } else {
                        showToast("로그인 실패, 아이디와 비밀번호를 확인해주세요.")
                    }

                }

            }
        }
    }

    //액티비티 이동
    fun startActivityWithClear(activity: Class<*>?) {
        val intent = Intent(context, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}