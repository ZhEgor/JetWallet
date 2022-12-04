package com.zhiroke.features.mywallet.presentation.cardcarousel

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.BaseReducer

internal class CardCarouselReducer : BaseReducer<CardCarouselState> {

    override val initialState: CardCarouselState get() = CardCarouselState.initialState()

    override fun reduce(state: CardCarouselState, event: BaseEvent): CardCarouselState {
        return state.run {
            when(event) {
                CardCarouselEvent.LoadCards -> copy(areCardsLoading = true)
                is CardCarouselEvent.LoadedCards -> copy(cards = event.cards, areCardsLoading = false) // ToDo: Add use case for subscription
                is CardCarouselEvent.FailedToLoadCards -> copy(errorMessage = event.errorMessage)
                is CardCarouselEvent.ChangeStateOfCreatePopUp -> copy(createCardPopUp = createCardPopUp.copy(isShown = event.show))
                is CardCarouselEvent.AddCard -> this
                is CardCarouselEvent.AddedCardSuccessfully -> this
                else -> throw IllegalArgumentException("Unsupported Event!")
            }
        }
    }
}