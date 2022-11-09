package com.todaypebble.pebbles.view.splash.activity

import android.os.Handler
import android.os.Looper
import com.bit.kodari.Config.BaseActivity
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.ActivitySplashBinding
import com.todaypebble.pebbles.view.login.activity.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun initAfterBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
            startNextActivity(LoginActivity::class.java)
            finish()
        },2000)
    }

}
