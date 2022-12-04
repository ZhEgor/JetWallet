package com.zhiroke.features.mywallet.presentation.cardcarousel.interactors

import com.zhiroke.core.common.base.BaseInteractor
import com.zhiroke.core.common.base.error.ErrorInteractor
import com.zhiroke.core.components.utils.toStableList
import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepository
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselEvent
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

internal class LoadCardsInteractor(private val bankCardRepository: BankCardRepository)
    : BaseInteractor<CardCarouselState, CardCarouselEvent>, ErrorInteractor {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(state: CardCarouselState, event: CardCarouselEvent) = tryCatch(
        onFailureHandler = { e ->
            CardCarouselEvent.FailedToLoadCards(errorMessage = e.message)
        }
    ) {

        val cardsFlow = bankCardRepository.fetchAll().flatMapLatest { list -> flowOf(list.toStableList()) }
        CardCarouselEvent.LoadedCards(cards = cardsFlow)
    }

    override fun canHandle(event: CardCarouselEvent) = event is CardCarouselEvent.LoadCards
}