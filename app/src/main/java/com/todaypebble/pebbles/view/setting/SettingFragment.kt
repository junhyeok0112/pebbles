package com.todaypebble.pebbles.view.setting

import androidx.navigation.findNavController
import com.bit.kodari.Config.BaseFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.FragmentSettingBinding
import com.todaypebble.pebbles.util.getNickname

class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun initAfterBinding() {
        initListener()
        binding.settingNicknameTv.text = getNickname()
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

        binding.settingBackBtn.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }


}
