package com.zhiroke.core.navigation.di.modules

import com.zhiroke.core.navigation.domain.NavigationManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


fun navigationModule() = module {

    singleOf(::NavigationManager)

}