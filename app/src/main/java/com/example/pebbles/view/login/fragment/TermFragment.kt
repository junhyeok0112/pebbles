package com.example.pebbles.view.login.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentTermBinding
import com.example.pebbles.view.MainActivity
import com.example.pebbles.view.login.LoginViewModel

//이용 약관 동의 프래그먼트
class TermFragment : BaseFragment<FragmentTermBinding>(R.layout.fragment_term) {

    private val loginViewModel : LoginViewModel by activityViewModels()
    private var flag = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//ViewPager안에 있는 Fragment의 버튼 눌렀을 때 다음 프래그먼트로 넘어가기
//부모 액티비티 전체에서 ViewPager 찾아서 접근하면됨
        val viewPager = activity?.findViewById<ViewPager2>(R.id.sign_up_viewpager)
        binding.targetCompleteBtn.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }

    override fun initAfterBinding() {
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this

        setListener()
    }

    private fun setListener(){
        binding.termChkIv.setOnClickListener {
            when(flag){
              false -> {    //이용 동의로 체크
                  binding.termChkIv.setImageResource(R.drawable.term_selected)
                  binding.termContainer.setBackgroundResource(R.drawable.corner_main10_8dp)
                  binding.targetCompleteBtn.isClickable = true
                  binding.targetCompleteBtn.setBackgroundResource(R.drawable.corner_main_30)
                  flag = true
              }
              else-> {
                  binding.termChkIv.setImageResource(R.drawable.term_unselected)
                  binding.termContainer.setBackgroundResource(R.drawable.corner_gray20_8dp)
                  binding.targetCompleteBtn.isClickable = false
                  binding.targetCompleteBtn.setBackgroundResource(R.drawable.corner_solid_gray_30_8dp)
                  flag = false
              }
            }
        }

        binding.termNextBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_signUpFragment_to_webTermFragment)
        }
    }

//액티비티 이동
    fun startActivityWithClear(activity: Class<*>?) {
        val intent = Intent(context, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}