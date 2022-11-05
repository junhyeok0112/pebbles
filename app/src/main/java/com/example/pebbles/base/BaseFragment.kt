package com.bit.kodari.Config


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.pebbles.base.LoadingDialog


abstract class BaseFragment<VB : ViewDataBinding>(
    @LayoutRes val layoutRes: Int
) : Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    //protected lateinit var binding:VB
    private lateinit var mLoadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initAfterBinding()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }

    protected abstract fun initAfterBinding()

    fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    //API 호출해서 데이터 불러오는 동안 로딩바 뜨는거
    fun showLoadingDialog(context:Context){
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