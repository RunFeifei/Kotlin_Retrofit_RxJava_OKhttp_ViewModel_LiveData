package com.uestc.medicine.paging

import android.util.Log
import android.util.SparseArray
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.uestc.medicine.adapter.DishPagingAdapter
import com.uestc.medicine.adapter.SangeListDiffer
import com.uestc.medicine.net.DishShopEntity
import com.uestc.request.RequestContext

/**
 * Created by PengFeifei on 2019-11-29.
 */


class ShopDataSource(private var initialKey: Int = 0) {

    private var nextPage: Int = initialKey
    private var previousPage: Int = initialKey
    private var pagingListDiffer = SangeListDiffer()
    private var dataRepo = ListDatas()

    val dishes: SparseArray<List<DishShopEntity>>? by lazy {
        RequestContext.getContext().applicationContext.assets.open("dishshop.json").use {
            JsonReader(it.reader()).use {
                val type = object : TypeToken<List<DishShopEntity>>() {}.type
                val list = Gson().fromJson<List<DishShopEntity>?>(it, type)
                return@use list.preProcess()
            }
        }
    }

    fun getItem(position: Int, fromBind: Boolean): DishShopEntity {
        Log.e("TAG--getItemInner", "-----$position")
        dispatchLoad(position)
        return pagingListDiffer.get(position)
    }

    fun getItemCount(): Int {
        return pagingListDiffer.size()
    }

    private fun dispatchLoad(position: Int) {
        if (position == 0) {
            Log.e("TAG--dispatchLoad", "-----$position----loadPrevious")
            loadPrevious()
        }
        if (position == getItemCount() - 1) {
            Log.e("TAG--dispatchLoad", "-----$position----loadAfter")
            loadAfter()
        }
    }

    fun loadInitial() {
        dishes?.let {
            it[initialKey]?.let { its ->
                setResult(isInitTial = true, isPrevious = false, data = its)
            }
        }
    }

    private fun loadAfter() {
        nextPage++
        dishes?.let {
            it[nextPage]?.let { its ->
                setResult(isInitTial = false, isPrevious = false, data = its)
            }
        }
    }

    private fun loadPrevious() {
        previousPage--
        dishes?.let {
            it[previousPage]?.let { its ->
                setResult(isInitTial = false, isPrevious = true, data = its)
            }
        }
    }

    fun invalidate(initialKey: Int) {
        this.initialKey = initialKey
        nextPage = initialKey
        previousPage = initialKey
        loadInitial()
    }

    fun setAdapter(adapter: DishPagingAdapter?) {
        pagingListDiffer.adapter = adapter
        invalidate(initialKey)
    }

    fun setResult(isInitTial: Boolean, isPrevious: Boolean, data: List<DishShopEntity>?) {
        data ?: return
        if (isInitTial) {
            dataRepo.clear()
            dataRepo.addAll(data)
            pagingListDiffer.submitList(dataRepo.get())
            Log.e("TAG--setResult--init", "-----${data.size}----${dataRepo.size()}")
            return
        }

        if (isPrevious) {
            dataRepo.addAll(0, data)
        } else {
            dataRepo.addAll(data)
        }
        Log.e("TAG--setResult--$isPrevious", "-----${data.size}----${dataRepo.size()}")
        pagingListDiffer.submitList(dataRepo.get())
    }


}

class ListDatas {
    private val datas = mutableListOf<DishShopEntity>()

    fun get(): List<DishShopEntity> {
        val result = mutableListOf<DishShopEntity>()
        result.addAll(datas)
        return result
    }

    fun size() = datas.size

    fun addAll(t: List<DishShopEntity>) {
        datas.addAll(t)
    }

    fun addAll(position: Int, t: List<DishShopEntity>) {
        datas.addAll(position, t)
    }

    fun clear() {
        datas.clear()
    }
}


fun List<DishShopEntity>?.preProcess(): SparseArray<List<DishShopEntity>>? {
    this ?: return null
    val array = SparseArray<List<DishShopEntity>>()
    var key = 0
    var lastIndex = 0

    forEachIndexed { index, item ->
        val next = if (index + 1 >= size) null else this[index + 1]
        val last = if (index - 1 < 0) null else this[index - 1]

        item.isEndOfType = next == null || next.isHeader
        item.textOFtype = if (item.isHeader || last == null) item.name else last.textOFtype

        if (next != null && next.isHeader) {
            array.put(key, this.subList(lastIndex, index + 1))
            key++
            lastIndex = index + 1
        }
        if (index == size - 1) {
            array.put(key, this.subList(lastIndex, size))
        }
    }
    return array
}
