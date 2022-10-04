package com.example.pebbles.view.login.activity

import com.bit.kodari.Config.BaseActivity
import com.example.pebbles.R
import com.example.pebbles.databinding.ActivityLoginBinding
import com.example.pebbles.view.MainActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun initAfterBinding() {
//Content에서 간단한 회원가입 -> 굵게 처리

//Listener 설정
        initListener()
    }

    fun initListener(){
        binding.loginKakaoBtn.setOnClickListener {
            startNextActivity(MainActivity::class.java)
            finish()
        }
        binding.loginNaverBtn.setOnClickListener {
            startNextActivity(MainActivity::class.java)
            finish()
        }
    }
}