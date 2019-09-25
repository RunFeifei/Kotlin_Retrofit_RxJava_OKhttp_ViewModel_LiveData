package com.uestc.medicine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uestc.medicine.base.BaseViewModel
import com.uestc.medicine.net.Api
import com.uestc.medicine.net.User
import com.uestc.request.request.ObservableHandler
import io.reactivex.Observable


/**
 * Created by PengFeifei on 2018/10/27.
 */

class LoginViewModel : BaseViewModel() {

    val user = MutableLiveData<User>()

    private fun login(loginname: String, pwd: String): Observable<User> {
        return ObservableHandler.getObservable(
            ObservableHandler.getService(Api::class.java).login(
                loginname,
                pwd
            )
        )
    }


    fun loadUser(loginname: String, pwd: String) {
        val disposable = login(loginname,pwd)
            .doOnSubscribe { onSubscribe() }
            .doFinally { onFinally() }
            .subscribe({ model -> user.postValue(model) }, { this.onException(it) })
    }


}
