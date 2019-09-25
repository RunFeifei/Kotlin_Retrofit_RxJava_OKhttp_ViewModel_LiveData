package com.uestc.medicine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uestc.medicine.base.BaseViewModel
import com.uestc.medicine.net.Api
import com.uestc.medicine.net.MenuDetails
import com.uestc.request.request.ObservableHandler
import io.reactivex.Observable


/**
 * Created by PengFeifei on 2018/10/27.
 */

class MenuDetailViewModel : BaseViewModel() {

    val data = MutableLiveData<MenuDetails>()

    val knowledgeBean = MutableLiveData<MenuDetails>()

    private fun getknowledgebycolumnid(
        column_id: String,
        user_id: String
    ): Observable<MenuDetails> {
        return ObservableHandler.getObservable(
            ObservableHandler.getService(Api::class.java).getknowledgebycolumnid(
                column_id,
                user_id
            )
        )
    }


    private fun getknowledgebyknowledgeid(knowledge_id: String): Observable<MenuDetails> {
        return ObservableHandler.getObservable(
            ObservableHandler.getService(Api::class.java).getknowledgebyknowledgeid(
                knowledge_id
            )
        )
    }

    fun loadKnowledge(knowledge_id: String) {
        val disposable = getknowledgebyknowledgeid(knowledge_id)
            .doOnSubscribe { onSubscribe() }
            .doFinally { onFinally() }
            .subscribe({ model -> knowledgeBean.postValue(model) }, { this.onException(it) })
    }


    fun loadData(column_id: String, user_id: String) {
        val disposable = getknowledgebycolumnid(column_id, user_id)
            .doOnSubscribe { onSubscribe() }
            .doFinally { onFinally() }
            .subscribe({ model -> data.postValue(model) }, { this.onException(it) })
    }


}
