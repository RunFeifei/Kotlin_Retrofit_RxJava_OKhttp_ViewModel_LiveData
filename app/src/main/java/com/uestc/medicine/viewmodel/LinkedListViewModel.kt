package com.uestc.medicine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.uestc.medicine.base.BaseViewModel
import com.uestc.medicine.net.DishBrandEntity
import com.uestc.medicine.paging.ShopDataSource
import com.uestc.request.RequestContext


/**
 * Created by PengFeifei on 2018/10/27.
 */

class LinkedListViewModel : BaseViewModel() {

    /************************* Brands **************************************/
    val listBrands: LiveData<List<DishBrandEntity>> by lazy {
        val live = MutableLiveData<List<DishBrandEntity>>()
        live.value = brands
        return@lazy live
    }


    private val brands: List<DishBrandEntity>? by lazy {
        RequestContext.getContext().applicationContext.assets.open("dishbrand.json").use {
            JsonReader(it.reader()).use {
                val type = object : TypeToken<List<DishBrandEntity>>() {}.type
                return@use Gson().fromJson<List<DishBrandEntity>?>(it, type)
            }
        }
    }
    /************************* Shops **************************************/
    var shopDataSource: ShopDataSource = ShopDataSource(0)

    fun reBuildPaging(typeClickIndex: Int) {
        shopDataSource.invalidate(typeClickIndex)
    }

}





