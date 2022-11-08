package com.zhiroke.features.mywallet.presentation

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.BaseReducer

internal class CardCarouselReducer : BaseReducer<CardCarouselState> {

    override val initialState: CardCarouselState get() = CardCarouselState.initialState()

    override fun reduce(state: CardCarouselState, event: BaseEvent): CardCarouselState {
        return state.run {
            when(event) {
                CardCarouselEvent.LoadCards -> copy(areCardsLoading = true)
                is CardCarouselEvent.LoadedCards -> copy(cards = event.cards, areCardsLoading = false)
                is CardCarouselEvent.FailedToLoadCards -> copy(errorMessage = event.errorMessage)
                else -> throw IllegalArgumentException("Unsupported Event!")
            }
        }
    }
}