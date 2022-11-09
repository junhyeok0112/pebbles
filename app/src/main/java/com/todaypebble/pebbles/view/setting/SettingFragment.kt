package com.todaypebble.pebbles.view.setting

import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentSettingBinding

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
