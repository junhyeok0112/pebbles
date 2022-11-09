package com.example.pebbles.view.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.bit.kodari.Config.BaseFragment
import com.example.pebbles.R
import com.example.pebbles.databinding.FragmentWebTermBinding


class WebTermFragment : BaseFragment<FragmentWebTermBinding>(R.layout.fragment_web_term) {

    override fun initAfterBinding() {
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        binding.webview.loadUrl("https://olivine-shear-610.notion.site/951323d8e4054f1591136dc71e953ef8")
    }
}