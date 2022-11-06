package com.zhiroke.features.mywallet.presentation

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.BaseState
import com.zhiroke.core.common.base.BaseViewModel
import com.zhiroke.core.common.base.error.ErrorState
import com.zhiroke.core.components.utils.StableList
import com.zhiroke.core.components.utils.emptyStableList
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.interactors.LoadCardsInteractor

data class CardCarouselState(
    override val errorMessage: String?,
    val areCardsLoading: Boolean,
    val cards: StableList<BankCard>,
) : BaseState, ErrorState {

    companion object {
        fun initialState() = CardCarouselState(
            areCardsLoading = false,
            errorMessage = null,
            cards = emptyStableList(),
        )
    }
}

sealed interface CardCarouselEvent : BaseEvent {
    object LoadCards : CardCarouselEvent
    data class LoadedCards(val cards: StableList<BankCard>) : CardCarouselEvent
//    data class LoadedCardsWithError(val cards: StableList<BankCard>, val errorMessage: String?) : CardCarouselEvent, ErrorEvent(message = errorMessage)
}

internal class CardCarouselViewModel(
    reducer: CardCarouselReducer,
    loadCardsInteractor: LoadCardsInteractor,
) : BaseViewModel<CardCarouselState, CardCarouselEvent>(
    reducer = reducer, interactors = setOf(loadCardsInteractor)
) {

    init {
        loadCards()
    }

    private fun loadCards() {
        sendEvent(event = CardCarouselEvent.LoadCards)
    }
}