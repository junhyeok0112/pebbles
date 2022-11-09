package com.example.pebbles.view.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun initAfterBinding() {
        initListener()
    }

    private fun initListener(){
        binding.settingWithdrawalTv.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_withdrawalFragment)
        }

        binding.settingLogoutTv.setOnClickListener {
            val logoutDialog = LogoutDialog()
            logoutDialog.show(requireActivity().supportFragmentManager , "logoutDialog")
        }

        binding.settingTerms.setOnClickListener {
            it.findNavController().navigate(R.id.action_settingFragment_to_webTermFragment2)
        }

    }


}
