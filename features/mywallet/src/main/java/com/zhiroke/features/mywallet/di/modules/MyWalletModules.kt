package com.zhiroke.features.mywallet.di.modules

import org.koin.dsl.module

fun myWalletModules() = module {
    includes(
        interactorsModule(),
        reducersModule(),
        viewModelsModule()
    )
}