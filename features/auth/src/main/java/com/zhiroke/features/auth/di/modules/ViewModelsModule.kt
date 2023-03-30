package com.zhiroke.features.auth.di.modules

import com.zhiroke.core.common.utils.koin.viewModelOf
import com.zhiroke.features.auth.presentation.auth.AuthViewModel
import org.koin.dsl.module

internal fun viewModelsModule() = module {

    viewModelOf(::AuthViewModel)

}