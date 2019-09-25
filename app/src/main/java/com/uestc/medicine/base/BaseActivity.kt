package com.uestc.medicine.base

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.uestc.request.error.ErrorCode
import com.uestc.request.error.RequestException

/**
 * Created by PengFeifei on 2018/10/27.
 */

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity(), IBaseView {
    protected lateinit var viewModel: T
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        viewModel = initViewModel()
        progressDialog = initProgressDialog()
        viewModel.loadingState.observe(this, Observer<Boolean> {
            if (it == null) {
                return@Observer
            }
            if (it) showLoading() else hideLoading()
        })

        viewModel.toastLiveData.observe(this, Observer<String> {
            it?.apply {
                showToast(it)
            }
        })

        viewModel.netExceptionState.observe(this, Observer<Throwable> { throwable ->
            if (throwable == null) {
                return@Observer
            }
            if (handleException(throwable)) {
                return@Observer
            }
            if (throwable is RequestException && throwable.code == ErrorCode.LOGIN_ERR) {
                handleLogin()
                return@Observer
            }
            if (throwable is RequestException && throwable.code == ErrorCode.TIMEOUT_ERR) {
                hideLoading()
                return@Observer
            }
            //TODO
        })
        initPage(savedInstanceState)
    }


    protected abstract fun initViewModel(): T
    protected abstract fun initPage(savedInstanceState: Bundle?)
    protected abstract fun layoutId():Int

    /*****************************************************************************/

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        if (progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }

    override fun showLoading() {
        if (!progressDialog.isShowing) {
            progressDialog.show()
        }
    }

    override fun handleLogin() {

    }

    override fun handleException(throwable: Throwable): Boolean {
        Log.e("BaseViewModel--> ", throwable?.toString() ?: "did not get detail exception")
        return false
    }

    /*****************************************************************************/

    private fun initProgressDialog(): ProgressDialog {
        val progressDialog = ProgressDialog(this)
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.setCancelable(false)
        return progressDialog
    }

}
