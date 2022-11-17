package com.todaypebble.pebbles.view.login.activity

import android.graphics.Color
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.bit.kodari.Config.BaseActivity
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.ActivityLoginBinding
import com.todaypebble.pebbles.view.login.LoginViewModel
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

        //상태바 색상 변경
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.parseColor("#FFFFFF")
    }

}