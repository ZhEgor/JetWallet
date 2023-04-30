package com.zhiroke.presentation.auth.di.modules

import com.zhiroke.presentation.auth.utils.managers.BiometricAuthManager
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


internal fun utilsModule() = module {

    singleOf(::BiometricAuthManager)

}