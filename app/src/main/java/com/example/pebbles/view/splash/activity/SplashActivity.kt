package com.example.pebbles.view.splash.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bit.kodari.Config.BaseActivity
import com.example.pebbles.R
import com.example.pebbles.databinding.ActivitySplashBinding
import com.example.pebbles.view.login.activity.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initAfterBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
            startNextActivity(LoginActivity::class.java)
            finish()
        },2000)
    }

}
