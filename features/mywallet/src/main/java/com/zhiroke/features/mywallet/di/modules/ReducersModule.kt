package com.zhiroke.features.mywallet.di.modules

import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselReducer
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal fun reducersModule() = module {

    factoryOf(::CardCarouselReducer)

}