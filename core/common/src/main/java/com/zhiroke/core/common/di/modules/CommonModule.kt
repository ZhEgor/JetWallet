package com.zhiroke.core.common.di.modules

import com.zhiroke.core.common.coroutines.AppDispatchersProvider
import com.zhiroke.core.common.coroutines.DispatchersProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun commonModule() = module {

    factoryOf(::AppDispatchersProvider) { bind<DispatchersProvider>() }

}