package com.example.pebbles.view.home.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bit.kodari.Config.BaseActivity
import com.example.pebbles.R
import com.example.pebbles.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initAfterBinding() {
        showToast("메인")
    }

}