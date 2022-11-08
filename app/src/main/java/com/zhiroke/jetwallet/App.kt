package com.zhiroke.jetwallet

import android.app.Application
import com.zhiroke.features.mywallet.di.modules.myWalletModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(myWalletModules)
        }
    }
}