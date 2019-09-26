package com.uestc.medicine.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.uestc.medicine.R
import com.uestc.medicine.base.BaseActivity
import com.uestc.medicine.net.MenuDetails
import com.uestc.medicine.util.Common
import com.uestc.medicine.util.click
import com.uestc.medicine.util.get
import com.uestc.medicine.viewmodel.MenuDetailViewModel
import kotlinx.android.synthetic.main.activity_menu_details.*


class MenuDetailsActivity : BaseActivity<MenuDetailViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_menu_details
    }

    override fun initViewModel(): MenuDetailViewModel {
        return get(MenuDetailViewModel::class.java)
    }

    override fun initPage(savedInstanceState: Bundle?) {
        intent.getStringExtra("column_id")?.let {
            viewModel.loadData(it,"")
        }
        viewModel.data.observe(this, Observer {
            renderView(it)
        })
    }

    private fun renderView(data: MenuDetails?) {
        data?.let {
            it.knowledge?.let { kns ->
                if (kns.isNullOrEmpty()) {
                    return
                }
                val content = kns[0]
                Glide.with(this).load(content.face_url).into(img)
                this.title = content.name
                img.click {
                    var intent = Intent(this, WebActivity::class.java)
                    intent.putExtra("url", content.url)
                    intent.putExtra("knowledge_id", content.knowledge_id)
                    startActivity(intent)
                }
            }
        }
    }


}
