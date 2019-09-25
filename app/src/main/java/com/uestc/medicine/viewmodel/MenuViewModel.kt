package com.uestc.medicine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uestc.medicine.base.BaseViewModel
import com.uestc.medicine.net.Api
import com.uestc.medicine.net.Menu
import com.uestc.medicine.net.User
import com.uestc.request.request.ObservableHandler
import io.reactivex.Observable


/**
 * Created by PengFeifei on 2018/10/27.
 */

class MenuViewModel : BaseViewModel() {

    val menu = MutableLiveData<Menu>()

    private fun menu(company_id: String, parent_id: String): Observable<Menu> {
        return ObservableHandler.getObservable(
            ObservableHandler.getService(Api::class.java).getMenus(
                company_id,
                parent_id
            )
        )
    }


    fun loadMenu(company_id: String, parent_id: String) {
        val disposable = menu(company_id,parent_id)
            .doOnSubscribe { onSubscribe() }
            .doFinally { onFinally() }
            .subscribe({ model -> menu.postValue(model) }, { this.onException(it) })
    }


}
