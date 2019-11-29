package com.uestc.medicine.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.uestc.medicine.base.BaseViewModel
import com.uestc.medicine.net.ShoeEntity
import com.uestc.medicine.paging.ShoeSourceFactory
import com.uestc.request.RequestContext


/**
 * Created by PengFeifei on 2018/10/27.
 */

class PagingViewModel : BaseViewModel() {

    companion object {
        val PagingConfig: PagedList.Config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .build()
    }

    val forkedApiData: List<ShoeEntity>? by lazy {
        initDatas(RequestContext.getContext())
    }

    val pagedShoesData: LiveData<PagedList<ShoeEntity>> =
        LivePagedListBuilder<Int, ShoeEntity>(ShoeSourceFactory(this), PagingConfig).build()


    private fun initDatas(context: Context): List<ShoeEntity>? {
        return context.applicationContext.assets.open("shoes.json").use {
            JsonReader(it.reader()).use {
                val shoeType = object : TypeToken<List<ShoeEntity>>() {}.type
                return@use Gson().fromJson<List<ShoeEntity>?>(it, shoeType)
            }
        }
    }


}
