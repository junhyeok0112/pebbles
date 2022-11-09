package com.todaypebble.pebbles.view.onboarding

import androidx.viewpager2.widget.ViewPager2
import com.bit.kodari.Config.BaseActivity
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.ActivityOnBoardingBinding
import com.todaypebble.pebbles.util.getAutoLogin
import com.todaypebble.pebbles.view.MainActivity
import com.todaypebble.pebbles.view.login.activity.LoginActivity
import com.todaypebble.pebbles.view.onboarding.adapter.OnBoardingVPAdapter

class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>(R.layout.activity_on_boarding) {

    //현재 ViewPager 위치
    private var curPosition = 0


    override fun initAfterBinding() {
        initViewPager()
        initListener()
    }

    private fun initViewPager() {
        //어댑터 설정
        binding.onboardingVp.adapter = OnBoardingVPAdapter(this)
        //Viewpager2 객체의 방향 설정
        binding.onboardingVp.offscreenPageLimit = 3

        //indicator 생성
        binding.onboardingIndicator.setViewPager(binding.onboardingVp)
        binding.onboardingIndicator.createIndicators(3, 0)

        binding.onboardingVp.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                curPosition = position
                binding.onboardingIndicator.animatePageSelected(position)
            }
        })
    }

    private fun initListener() {
        binding.onboardingNextBtn.setOnClickListener {
            //클릭 했을 때 다음꺼 아니면 마지막
            when (curPosition) {
                2 -> {    //마지막에서 다음 누르면 로그인 페이지 실행
                    if (getAutoLogin()) {
                        startActivityWithClear(MainActivity::class.java)
                    } else {

                        startActivityWithClear(LoginActivity::class.java)
                    }

                }
                else -> {
                    curPosition++
                    binding.onboardingVp.setCurrentItem(curPosition)
                }
            }
        }
    }

}