package com.uestc.medicine.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.uestc.medicine.R
import com.uestc.medicine.adapter.ShoeDiffCallback
import com.uestc.medicine.adapter.ShoesAdapter
import com.uestc.medicine.base.BaseActivity
import com.uestc.medicine.net.ShoeEntity
import com.uestc.medicine.util.get
import com.uestc.medicine.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_paging.*

/**
 * Created by PengFeifei on 2019-11-28.
 */

class PagingActivity : BaseActivity<PagingViewModel>() {

    override fun initViewModel(): PagingViewModel {
        return get(PagingViewModel::class.java)
    }

    override fun layoutId(): Int {
        return R.layout.activity_paging
    }

    override fun initPage(savedInstanceState: Bundle?) {
        val adapter=ShoesAdapter(ShoeDiffCallback())
        listView.adapter=adapter
        viewModel.pagedShoesData.observe(this, Observer<PagedList<ShoeEntity>> {
            adapter.submitList(it)
        })
    }


}