package com.zhiroke.features.mywallet.di.modules

import org.koin.dsl.module

val myWalletModules get() = module {
    includes(
        interactorsModule,
        reducersModule,
        viewModelsModule
    )
}