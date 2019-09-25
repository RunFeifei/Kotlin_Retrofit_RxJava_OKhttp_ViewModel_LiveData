package com.uestc.medicine.ui

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.uestc.medicine.R
import com.uestc.medicine.base.BaseActivity
import com.uestc.medicine.util.Common
import com.uestc.medicine.util.get
import com.uestc.medicine.viewmodel.StudyViewModel
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseActivity<StudyViewModel>() {


    override fun layoutId(): Int {
        return R.layout.activity_web
    }

    override fun initViewModel(): StudyViewModel {
        return get(StudyViewModel::class.java)
    }

    override fun initPage(savedInstanceState: Bundle?) {
        initWeb()
        actionBar?.hide()
        intent?.let {
            viewModel.load(it.getStringExtra("knowledge_id"), Common.UID)
            webView.loadUrl(it.getStringExtra("url"))
        }
        viewModel.study.observe(this, Observer {
            it?.let {
                Toast.makeText(this, "学习中", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initWeb() {
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.saveFormData = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.setSupportZoom(true)
        webView.settings.textZoom = 100
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        //add for local storage
        webView.settings.domStorageEnabled = true
        webView.settings.databaseEnabled = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.allowFileAccess = true

        webView.settings.setAppCacheEnabled(true)
        webView.settings.setAppCachePath(cacheDir?.absolutePath)
        webView.settings.setAppCacheMaxSize(1024 * 1024 * 8)
    }


}
