package com.uestc.medicine.viewmodel

import androidx.lifecycle.MutableLiveData
import com.uestc.medicine.base.BaseViewModel
import com.uestc.medicine.net.Api
import com.uestc.medicine.net.Study
import com.uestc.request.request.ObservableHandler
import io.reactivex.Observable


/**
 * Created by PengFeifei on 2018/10/27.
 */

class StudyViewModel : BaseViewModel() {

    val study = MutableLiveData<Study>()

    private fun study(knowledge_id: String,  user_id: String): Observable<Study> {
        return ObservableHandler.getObservable(
            ObservableHandler.getService(Api::class.java).study(
                knowledge_id,
                user_id
            )
        )
    }


    fun load(knowledge_id: String,  user_id: String) {
        val disposable = study(knowledge_id, user_id)
            .doOnSubscribe { onSubscribe() }
            .doFinally { onFinally() }
            .subscribe({ model -> study.postValue(model) }, { this.onException(it) })
    }

}
