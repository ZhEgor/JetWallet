package com.zhiroke.jetwallet.utils.initmanager

import android.app.Application
import com.zhiroke.core.common.di.modules.coroutineModule
import com.zhiroke.core.navigation.di.modules.navigationModule
import com.zhiroke.data.di.modules.dataModule
import com.zhiroke.domain.di.modules.repositoriesModule
import com.zhiroke.features.mywallet.di.modules.myWalletModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinInitManager(private val application: Application) : InitManager {

    override fun init() {
        startKoin {
            androidContext(application)
            modules(
                dataModule(),
                repositoriesModule(),
                navigationModule(),
                coroutineModule(),
                myWalletModules()
            )
        }
    }
}