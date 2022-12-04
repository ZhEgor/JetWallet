package com.zhiroke.features.mywallet.di.modules

import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.LoadCardsInteractor
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.AddCardInteractor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal fun interactorsModule() = module {

    factoryOf(::LoadCardsInteractor)
    factoryOf(::AddCardInteractor)

}