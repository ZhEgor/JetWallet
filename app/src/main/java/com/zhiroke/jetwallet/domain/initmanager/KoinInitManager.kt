package com.zhiroke.jetwallet.domain.initmanager

import android.app.Application
import com.zhiroke.features.mywallet.di.modules.myWalletModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinInitManager(private val application: Application) : InitManager {

    override fun init() {
        startKoin {
            androidContext(application)
            modules(myWalletModules)
        }
    }
}