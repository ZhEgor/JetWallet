package com.zhiroke.presentation.mywallet.di.modules

import com.zhiroke.presentation.mywallet.presentation.cardcarousel.CardCarouselViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal fun viewModelsModule() = module {

    viewModelOf(::CardCarouselViewModel)

}