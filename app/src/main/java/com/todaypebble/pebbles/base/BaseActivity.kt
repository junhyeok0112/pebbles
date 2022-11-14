package com.bit.kodari.Config

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.todaypebble.pebbles.base.LoadingDialog

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes val layoutRes: Int): AppCompatActivity(){
    protected lateinit var binding: T
        private set

    private var imm : InputMethodManager? = null
    private lateinit var mLoadingDialog: LoadingDialog
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics
        initAfterBinding()
    }

    protected abstract fun initAfterBinding()

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun startNextActivity(activity: Class<*>?) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    fun startActivityWithClear(activity: Class<*>?) {
        val intent = Intent(this, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    // 키보드 숨기기
    fun hideKeyboard(v: View){
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }

    fun showLoadingDialog(context: Context){
        mLoadingDialog = LoadingDialog(context)
        mLoadingDialog.show()
    }

    //API 호출해서 데이터 불러오면 로딩바 없애는거
    fun dismissLoadingDialog(){
        if(mLoadingDialog.isShowing){
            mLoadingDialog.dismiss()
        }
    }
}