package com.uestc.medicine.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.uestc.medicine.R
import com.uestc.medicine.adapter.DishPagingAdapter
import com.uestc.medicine.adapter.ShopTypeAdapter
import com.uestc.medicine.adapter.WrapContentLinearLayoutManager
import com.uestc.medicine.base.BaseActivity
import com.uestc.medicine.net.DishBrandEntity
import com.uestc.medicine.util.get
import com.uestc.medicine.viewmodel.LinkedListViewModel
import kotlinx.android.synthetic.main.activity_linkedlist.*
import kotlinx.android.synthetic.main.activity_paging.listView

/**
 * Created by PengFeifei on 2019-11-28.
 */

class LinkedListActivity : BaseActivity<LinkedListViewModel>() {

    private lateinit var shopTypeAdapter: ShopTypeAdapter
    private lateinit var shopAdapter: DishPagingAdapter

    override fun initViewModel(): LinkedListViewModel {
        return get(LinkedListViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_linkedlist
    }

    override fun initPage(savedInstanceState: Bundle?) {
        initDataList()
        initTypeList()
    }

    private fun initDataList() {
        shopAdapter = DishPagingAdapter(viewModel.shopDataSource)
        listView.adapter = shopAdapter
        listView.layoutManager = WrapContentLinearLayoutManager(this)
    }

    private fun initTypeList() {
        shopTypeAdapter = ShopTypeAdapter()
        shopTypeAdapter.onItemClick = { position, view, dishBrandEntity ->
            viewModel.reBuildPaging(position)
        }
        listTypes.adapter = shopTypeAdapter
        viewModel.listBrands.observe(this, Observer<List<DishBrandEntity>> {
            shopTypeAdapter.datas = it
            shopTypeAdapter.notifyDataSetChanged()
        })
    }


}