package com.zhiroke.presentation.auth.di.modules

import com.zhiroke.core.common.utils.koin.viewModelOf
import com.zhiroke.presentation.auth.presentation.auth.AuthViewModel
import org.koin.dsl.module

internal fun viewModelsModule() = module {

    viewModelOf(::AuthViewModel)

}