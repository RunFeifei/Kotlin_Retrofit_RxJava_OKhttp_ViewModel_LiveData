package com.uestc.medicine.paging

import androidx.paging.PageKeyedDataSource
import com.uestc.medicine.net.ShoeEntity
import com.uestc.medicine.viewmodel.PagingViewModel

/**
 * Created by PengFeifei on 2019-11-28.
 */
class ShoeDataSource(private val viewModel: PagingViewModel) : PageKeyedDataSource<Int, ShoeEntity>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ShoeEntity>) {
        viewModel.forkedApiData?.let {
            callback.onResult(it, 0, 1)
        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ShoeEntity>) {

        viewModel.forkedApiData?.let {
            callback.onResult(it, params.key + 1)
        }


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ShoeEntity>) {

    }
}