package com.todaypebble.pebbles.view.setting

import android.content.Intent
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentWithdrawalBinding
import com.todaypebble.pebbles.util.getUserIdx
import com.todaypebble.pebbles.util.saveAutoLogin
import com.todaypebble.pebbles.util.saveLoginInfo
import com.todaypebble.pebbles.view.home.HomeViewModel
import com.todaypebble.pebbles.view.login.activity.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
                withContext(Dispatchers.Main) {
                    if (body != null && body.isSuccess) {
                        //회원 탈퇴 성공 시
                        saveLoginInfo(null, null, null, 0)     //0이면 유저 없는거
                        saveAutoLogin(false)
                        startActivity(Intent(requireContext(), LoginActivity::class.java))
                        requireActivity().finish()
                    } else {
                        showToast("회원 탈퇴 시 오류가 발생하였습니다")
                    }
                }

            }
        }
    }
}