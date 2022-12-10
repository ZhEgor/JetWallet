package com.zhiroke.features.mywallet.presentation.cardcarousel

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
                is CardCarouselEvent.ChangeStateOfAddPopUp -> copy(addCardPopUp = addCardPopUp.copy(isShown = event.show))
                is CardCarouselEvent.ChangeStateOfEditPopUp -> copy(editCardPopUp = editCardPopUp.copy(isShown = event.show), cardToEdit = event.cardToEdit)
                is CardCarouselEvent.AddCard -> this
                CardCarouselEvent.AddedCardSuccessfully -> this
                is CardCarouselEvent.SaveCard -> this
                CardCarouselEvent.SavedCardSuccessfully -> this
                is CardCarouselEvent.DeleteCard -> this
                is CardCarouselEvent.DeletedCardSuccessfully -> this
                else -> throw IllegalArgumentException("Unsupported Event!")
            }
        }
    }
}