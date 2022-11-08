package com.zhiroke.features.mywallet.di.modules

import com.zhiroke.features.mywallet.presentation.CardCarouselViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val viewModelsModule = module {

    viewModelOf(::CardCarouselViewModel)

}