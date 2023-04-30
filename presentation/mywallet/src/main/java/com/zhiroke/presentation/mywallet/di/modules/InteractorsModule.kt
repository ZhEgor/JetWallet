package com.zhiroke.presentation.mywallet.di.modules

import com.zhiroke.presentation.mywallet.presentation.cardcarousel.interactors.AddCardInteractor
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.interactors.DeleteCardInteractor
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.interactors.LoadCardsInteractor
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.interactors.SaveCardInteractor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


internal fun interactorsModule() = module {

    factoryOf(::LoadCardsInteractor)
    factoryOf(::AddCardInteractor)
    factoryOf(::SaveCardInteractor)
    factoryOf(::DeleteCardInteractor)

}