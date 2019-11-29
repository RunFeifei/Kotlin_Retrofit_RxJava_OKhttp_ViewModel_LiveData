package com.uestc.medicine.paging

import androidx.paging.DataSource
import com.uestc.medicine.net.ShoeEntity
import com.uestc.medicine.viewmodel.PagingViewModel

/**
 * Created by PengFeifei on 2019-11-28.
 */
class ShoeSourceFactory constructor(private val viewModel: PagingViewModel) :
    DataSource.Factory<Int, ShoeEntity>() {
    override fun create(): DataSource<Int, ShoeEntity> {
        return ShoeDataSource(viewModel)
    }
}