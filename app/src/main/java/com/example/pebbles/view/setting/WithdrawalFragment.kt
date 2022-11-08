package com.example.pebbles.view.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentWithdrawalBinding


class WithdrawalFragment : BaseFragment<FragmentWithdrawalBinding>(R.layout.fragment_withdrawal) {

    override fun initAfterBinding() {


        initListener()
    }

    private fun initListener(){
        binding.withdrawalInputReasonEt.addTextChangedListener {
            if(binding.withdrawalInputReasonEt.text.length > 0) {
                binding.withdrawalBtn.setBackgroundResource(R.drawable.withdrawal_selected_btn)
                binding.withdrawalBtn.isClickable = true
            } else{
                binding.withdrawalBtn.setBackgroundResource(R.drawable.withdrawal_unselected_btn)
                binding.withdrawalBtn.isClickable = false
            }
        }
    }
}