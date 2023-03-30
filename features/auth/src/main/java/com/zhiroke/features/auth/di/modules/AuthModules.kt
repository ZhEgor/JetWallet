package com.zhiroke.features.auth.di.modules

import org.koin.dsl.module

fun authModules() = module {
    includes(
        interactorsModule(),
        reducersModule(),
        utilsModule(),
        viewModelsModule()
    )
}