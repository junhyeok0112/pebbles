package com.example.pebbles.view.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentNickNameBinding
import com.example.pebbles.viewmodel.login.LoginViewModel
import kotlin.math.log

//닉네임 설정 프래그먼트
class NickNameFragment : BaseFragment<FragmentNickNameBinding>(R.layout.fragment_nick_name) {

    private val loginViewModel : LoginViewModel by activityViewModels()

    override fun initAfterBinding() {

        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
        setListener()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//ViewPager안에 있는 Fragment의 버튼 눌렀을 때 다음 프래그먼트로 넘어가기
//부모 액티비티 전체에서 ViewPager 찾아서 접근하면됨
        val viewPager = activity?.findViewById<ViewPager2>(R.id.sign_up_viewpager)
        binding.nickNameNextBtn.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

    private fun setListener(){
        binding.nickNameCheckTv.setOnClickListener {
            showToast("클릭")
        }
    }
}