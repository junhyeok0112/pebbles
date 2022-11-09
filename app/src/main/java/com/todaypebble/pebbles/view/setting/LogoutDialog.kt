package com.todaypebble.pebbles.view.setting

import android.content.Intent
import com.bit.kodari.Config.BaseDialogFragment
import com.todaypebble.pebbles.R
import com.todaypebble.pebbles.databinding.DialogLogoutBinding
import com.todaypebble.pebbles.util.saveAutoLogin
import com.todaypebble.pebbles.util.saveLoginInfo
import com.todaypebble.pebbles.view.login.activity.LoginActivity

class LogoutDialog : BaseDialogFragment<DialogLogoutBinding>(R.layout.dialog_logout){
    override fun initAfterBinding() {

        initListener()

    }

    fun initListener(){
        binding.logoutDialogLogoutBtn.setOnClickListener {
            saveLoginInfo(null, null, null, 0)     //0이면 유저 없는거
            saveAutoLogin(false)
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        binding.logoutDialogCancelBtn.setOnClickListener {
            dismiss()
        }
    }
}