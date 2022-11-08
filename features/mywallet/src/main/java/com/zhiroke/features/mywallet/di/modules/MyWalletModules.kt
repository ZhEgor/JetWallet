package com.zhiroke.features.mywallet.di.modules

import org.koin.dsl.module

val myWalletModules = module {
    includes(
        interactorsModule,
        reducersModule,
        viewModelsModule
    )
}