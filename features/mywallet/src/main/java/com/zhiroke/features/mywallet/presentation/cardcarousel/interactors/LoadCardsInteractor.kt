package com.zhiroke.features.mywallet.presentation.cardcarousel.interactors

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SubscriptionInteractor
import com.zhiroke.core.common.base.error.SubscriptionErrorInteractor
import com.zhiroke.core.components.utils.toStableList
import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepository
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselEvent
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

internal class LoadCardsInteractor(private val bankCardRepository: BankCardRepository)
    : SubscriptionInteractor<CardCarouselState, CardCarouselEvent>, SubscriptionErrorInteractor {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun invoke(state: CardCarouselState, event: CardCarouselEvent): Flow<BaseEvent> = tryCatch(
        onFailureHandler = { e ->
            CardCarouselEvent.FailedToLoadCards(errorMessage = e.message)
        }
    ) {
        bankCardRepository.fetchAll().flatMapLatest { list ->
            flowOf(CardCarouselEvent.LoadedCards(cards = list.toStableList()))
        }
    }

    override fun canHandle(event: CardCarouselEvent) = event is CardCarouselEvent.LoadCards
}