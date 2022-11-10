package com.todaypebble.pebbles.view.manage.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentStoneAddBinding
import com.todaypebble.pebbles.view.login.adapter.SingUpVPAdapter
import com.todaypebble.pebbles.view.manage.adapter.StoneAddVPAdapter

//뷰페이저 있는 곳 ->여기서 StoneNick -> HabitAdd -> sandAdd -> Result 순으로
class StoneAddFragment : BaseFragment<FragmentStoneAddBinding>(R.layout.fragment_stone_add) {

    private lateinit var callback: OnBackPressedCallback

    //시스템 뒤로가기 눌렀을시 보이는 뷰페이저에 따라 다르게 작동하게 예외처리
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.stoneAddViewpager.currentItem == 1) {
                    binding.stoneAddViewpager.setCurrentItem(0)
                } else if (binding.stoneAddViewpager.currentItem == 2) {
                    binding.stoneAddViewpager.setCurrentItem(1)
                } else {
                    view?.findNavController()?.popBackStack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun initAfterBinding() {
        setViewPager()
        setListener()
    }

    //뷰페이저 2 설정 함수
    private fun setViewPager() {
        binding.stoneAddViewpager.adapter = StoneAddVPAdapter(this)
        binding.stoneAddViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.stoneAddViewpager.offscreenPageLimit = 3
        binding.stoneAddViewpager.isUserInputEnabled = false
        binding.stoneAddViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.stoneAddProgressBar.setProgressCompat(33, true)
                        binding.stoneAddTitleTv.text = "바윗돌 쌓기"
                    }
                    1 -> {
                        binding.stoneAddProgressBar.setProgressCompat(66, true)
                        binding.stoneAddTitleTv.text = "조약돌로 쪼개기"
                    }
                    else -> {
                        binding.stoneAddProgressBar.setProgressCompat(100, true)
                        binding.stoneAddTitleTv.text = "모래알로 쪼개기"
                    }
                }
            }
        })
    }

    private fun setListener() {
        binding.stoneAddBackIv.setOnClickListener {
            when (binding.stoneAddViewpager.currentItem) {
                0 -> {
                    it.findNavController().popBackStack()           //쌓여있는 스택으로
                }
                1 -> {
                    binding.stoneAddViewpager.setCurrentItem(0)
                }
                else -> {
                    binding.stoneAddViewpager.setCurrentItem(1)
                }
            }
        }

        binding.stoneAddNextBtn.setOnClickListener {
            when (binding.stoneAddViewpager.currentItem) {
                0 -> {
                    binding.stoneAddViewpager.setCurrentItem(1)        //쌓여있는 스택으로
                }
                1 -> {
                    binding.stoneAddViewpager.setCurrentItem(2)
                }
                else -> {
                    it.findNavController()
                        .navigate(R.id.action_stoneAddFragment_to_stoneAddResultFragment)
                }
            }
        }
    }

}