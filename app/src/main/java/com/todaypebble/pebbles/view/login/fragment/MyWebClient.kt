package com.todaypebble.pebbles.view.login.fragment

import android.net.http.SslError
import android.webkit.*

class MyWebClient : WebViewClient() {
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        handler!!.proceed()
    }


}