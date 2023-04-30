package com.zhiroke.presentation.auth.di.modules

import com.zhiroke.presentation.auth.presentation.auth.AuthReducer
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal fun reducersModule() = module {

    factoryOf(::AuthReducer)

}