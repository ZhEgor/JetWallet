package com.zhiroke.features.auth.di.modules

import com.zhiroke.features.auth.utils.managers.BiometricAuthManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal fun utilsModule() = module {

    singleOf(::BiometricAuthManager)

}