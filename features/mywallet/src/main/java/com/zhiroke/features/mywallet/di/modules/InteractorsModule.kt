package com.zhiroke.features.mywallet.di.modules

import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.LoadCardsInteractor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val interactorsModule = module {

    factoryOf(::LoadCardsInteractor)

}