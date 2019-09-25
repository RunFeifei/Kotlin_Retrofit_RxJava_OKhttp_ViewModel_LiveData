package com.uestc.medicine


import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

import com.uestc.request.host.BaseUrlBinder
import com.uestc.request.host.ServerType
import com.uestc.request.RequestContext

/**
 * Created by PengFeifei on 2018/10/27.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initNet()
    }

    private fun initNet() {
        RequestContext.setContext(this)
        BaseUrlBinder.resetBaseUrl(ServerType.PRODUCT)
        BaseUrlBinder.initBaseUrl("http://minisite.medtoday.com.cn/")
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
