package com.a90ms.opgg.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.a90ms.opgg.R
import com.a90ms.opgg.databinding.DialogLoadingBinding
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int
) : AppCompatActivity(), BaseInterface {

    protected lateinit var binding: VDB

    @Inject
    lateinit var loadingState: LoadingState

    private var loadingDialog: AppCompatDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        binding = DataBindingUtil.setContentView<VDB>(this, layoutResId).apply {
            lifecycleOwner = this@BaseActivity
        }
    }

    private fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = BaseDialog<DialogLoadingBinding>(
                this,
                R.layout.dialog_loading,
                enableDim = false
            )
        }
        if (loadingDialog?.isShowing == false && !isFinishing) {
            loadingDialog?.show()
        }
    }

    private fun hideLoading() {
        loadingDialog?.dismiss()
    }

    override fun loadingState(show: Boolean) {
        if (show) showLoading() else hideLoading()
    }
}
