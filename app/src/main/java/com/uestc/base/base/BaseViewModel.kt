package com.uestc.base.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


/**
 * Created by PengFeifei on 2018/10/27.
 */

open class BaseViewModel : ViewModel() {

    internal val toastLiveData = MutableLiveData<String>()
    internal val loadingState = MutableLiveData<Boolean>()
    internal val netExceptionState = MutableLiveData<Throwable>()


    private fun showLoading() {
        loadingState.postValue(true)
    }

    private fun hideLoading() {
        loadingState.postValue(false)
    }

    fun showToast(message: String) {
        toastLiveData.postValue(message)
    }

    protected fun onException(throwable: Throwable) {
        netExceptionState.postValue(throwable)
    }

    protected fun onSubscribe() {
        showLoading()
    }

    protected fun onFinally() {
        hideLoading()
    }



}
