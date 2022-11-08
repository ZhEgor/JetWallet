package com.zhiroke.features.mywallet.di.modules

import com.zhiroke.features.mywallet.presentation.CardCarouselReducer
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val reducersModule = module {

    factoryOf(::CardCarouselReducer)

}