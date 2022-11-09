package com.example.pebbles.view.login.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentNickNameBinding
import com.example.pebbles.util.StringUtil
import com.example.pebbles.view.login.LoginViewModel
import kotlinx.coroutines.*
import kotlin.math.log

//회원가입 프래그먼트
class NickNameFragment : BaseFragment<FragmentNickNameBinding>(R.layout.fragment_nick_name) {

    private val loginViewModel : LoginViewModel by activityViewModels()

    override fun initAfterBinding() {

        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = this
        loginViewModel.initSignUpData()
        setListener()
        setObserver()
    }



    private fun setListener(){
        //중복확인 체크
        binding.nickNameCheckTv.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                val result = loginViewModel.duplicateChk()
                withContext(Dispatchers.Main){
                    if(result != null){
                        if(!result){
                            loginViewModel.canUseNick.value = true
                        } else{
                            showToast("중복된 닉네임입니다.")
                        }
                    } else{
                        showToast("에러 발생")
                    }
                }
            }
        }

        binding.nickNameNextBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val signUpResponse = loginViewModel.signUp()
                if(signUpResponse.body()!!.isSuccess){
                    withContext(Dispatchers.Main){
                        showToast("회원가입 성공")
                        it.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                    }
                } else{
                    withContext(Dispatchers.Main){
                        showToast("회원가입 실패")
                        Log.d("회원가입","${signUpResponse.body()}")
                    }
                }
            }
        }
    }

    private fun setObserver(){
        loginViewModel.nickname.observe(this, {
            if(it.isEmpty()){       //비었으면 전부 회색
                binding.signUpSevenChk.setImageResource(R.drawable.login_unchecked)
                binding.signUpSevenText.setTextColor(ContextCompat.getColor(binding.root.context , R.color.gray_30))
                binding.signUpSpecialChk.setImageResource(R.drawable.login_unchecked)
                binding.signUpSpecialText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_30))
                loginViewModel.inSevenChk.value = false
                loginViewModel.cannotSpecial.value = false
            } else{
                //비어있지 않으면
                //7글자이내 인지 -> 근데 애초에 제한 해둬서 상관없음
                loginViewModel.inSevenChk.value = true
                //특수문자 확인해서 -> 빨간 ,파랑, 회색 구분
                //특수문자가 없으면 True ,즉 영어 , 숫자로만
                if(StringUtil.chkId(it)){
                    binding.signUpSpecialChk.setImageResource(R.drawable.login_checked)
                    binding.signUpSpecialText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main_30))
                    loginViewModel.cannotSpecial.value = true
                 } else{
                    binding.signUpSpecialChk.setImageResource(R.drawable.ic_login_alert)
                    binding.signUpSpecialText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.alert))
                    loginViewModel.cannotSpecial.value = false
                }
            }

            if(loginViewModel.inSevenChk.value!! && loginViewModel.cannotSpecial.value!!){
                loginViewModel.isduplicateChk.value = true
//                loginViewModel.canUseNick.value = true
            } else{
                loginViewModel.isduplicateChk.value = false
//                loginViewModel.canUseNick.value = false
            }
            loginViewModel.changeBtn()
        })

        loginViewModel.password.observe(this,{
            if(it.isEmpty()){
                binding.signUpPwEightChk.setImageResource(R.drawable.login_unchecked)
                binding.signUpPwEightText.setTextColor(ContextCompat.getColor(binding.root.context , R.color.gray_30))
                binding.signUpPwConstraintChk.setImageResource(R.drawable.login_unchecked)
                binding.signUpPwConstraintText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_30))
                loginViewModel.pwInEight.value = false
                loginViewModel.engNumSpecialTwo.value = false
            } else{
                if(it.length >= 8 && it.length <= 16){
                    binding.signUpPwEightChk.setImageResource(R.drawable.login_checked)
                    binding.signUpPwEightText.setTextColor(ContextCompat.getColor(binding.root.context , R.color.main_30))
                    loginViewModel.pwInEight.value = true
                } else{
                    binding.signUpPwEightChk.setImageResource(R.drawable.ic_login_alert)
                    binding.signUpPwEightText.setTextColor(ContextCompat.getColor(binding.root.context , R.color.alert))
                    loginViewModel.pwInEight.value = false
                }

                if(StringUtil.chkPw(it)){
                    binding.signUpPwConstraintChk.setImageResource(R.drawable.login_checked)
                    binding.signUpPwConstraintText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main_30))
                    loginViewModel.engNumSpecialTwo.value = true
                } else{
                    binding.signUpPwConstraintChk.setImageResource(R.drawable.ic_login_alert)
                    binding.signUpPwConstraintText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.alert))
                    loginViewModel.engNumSpecialTwo.value = false
                }
            }
            loginViewModel.changeBtn()
        })

        loginViewModel.password_chk.observe(this, {
            if(it.isEmpty()){
                binding.signUpSameChk.setImageResource(R.drawable.login_unchecked)
                binding.signUpPwConstraintText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_30))
                loginViewModel.pwCoincide.value = false
            } else{
                if(loginViewModel.password.value == it){
                    binding.signUpSameChk.setImageResource(R.drawable.login_checked)
                    binding.signUpSameText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.main_30))
                    loginViewModel.pwCoincide.value = true
                } else{
                    binding.signUpSameChk.setImageResource(R.drawable.ic_login_alert)
                    binding.signUpSameText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.alert))
                    loginViewModel.pwCoincide.value = false
                }
            }
            loginViewModel.changeBtn()
        })

    }



}