package com.zhiroke.features.mywallet.di.modules

import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal fun viewModelsModule() = module {

    viewModelOf(::CardCarouselViewModel)

}