package com.zhiroke.jetwallet

import android.app.Application
import com.zhiroke.jetwallet.domain.initmanager.InitManager
import com.zhiroke.jetwallet.domain.initmanager.KoinInitManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initApp()
    }

    private fun initApp() {
        listOf<InitManager>(KoinInitManager(application = this@App)).forEach { initManager ->
            initManager.init()
        }
    }
}