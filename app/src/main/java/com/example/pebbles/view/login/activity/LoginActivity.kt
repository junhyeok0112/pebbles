package com.example.pebbles.view.login.activity

import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.bit.kodari.Config.BaseActivity
import com.example.pebbles.R
import com.example.pebbles.databinding.ActivityLoginBinding
import com.example.pebbles.view.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun initAfterBinding() {
        //네비게이션 컨트롤러 선언
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.login_fragment_controller) as NavHostFragment
        val navController = navHostFragment.navController

        //액티비티와 프래그먼트가 공유할 ViewModel 선언
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
    }

}