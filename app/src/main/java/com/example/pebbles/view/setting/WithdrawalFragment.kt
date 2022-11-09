package com.example.pebbles.view.setting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentWithdrawalBinding
import com.example.pebbles.util.getUserIdx
import com.example.pebbles.util.saveAutoLogin
import com.example.pebbles.util.saveLoginInfo
import com.example.pebbles.view.home.HomeViewModel
import com.example.pebbles.view.login.activity.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class WithdrawalFragment : BaseFragment<FragmentWithdrawalBinding>(R.layout.fragment_withdrawal) {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun initAfterBinding() {

        initListener()
    }

    private fun initListener() {
        binding.withdrawalInputReasonEt.addTextChangedListener {
            if (binding.withdrawalInputReasonEt.text.length > 0) {
                binding.withdrawalBtn.setBackgroundResource(R.drawable.withdrawal_selected_btn)
                binding.withdrawalBtn.isClickable = true
            } else {
                binding.withdrawalBtn.setBackgroundResource(R.drawable.withdrawal_unselected_btn)
                binding.withdrawalBtn.isClickable = false
            }
        }

        binding.withdrawalBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val withdrawalResponse = homeViewModel.withdrawal(getUserIdx())
                val body = withdrawalResponse.body()
                //성공했을 때
                if(body != null && body.isSuccess){
                    //회원 탈퇴 성공 시
                    saveLoginInfo(null, null, null, 0)     //0이면 유저 없는거
                    saveAutoLogin(false)
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                    requireActivity().finish()
                } else{
                    showToast("회원 탈퇴 시 오류가 발생하였습니다")
                }
            }
        }
    }
}