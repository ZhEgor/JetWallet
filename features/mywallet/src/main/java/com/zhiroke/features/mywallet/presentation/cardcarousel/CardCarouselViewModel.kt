package com.zhiroke.features.mywallet.presentation.cardcarousel

import androidx.annotation.RestrictTo
import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.BaseState
import com.zhiroke.core.common.base.BaseViewModel
import com.zhiroke.core.common.base.error.ErrorEvent
import com.zhiroke.core.common.base.error.ErrorState
import com.zhiroke.core.components.utils.StableList
import com.zhiroke.core.components.utils.emptyStableList
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.LoadCardsInteractor

internal data class CardCarouselState(
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

internal sealed interface CardCarouselEvent : BaseEvent {
    object LoadCards : CardCarouselEvent
    data class LoadedCards(val cards: StableList<BankCard>) : CardCarouselEvent
    data class FailedToLoadCards(override val errorMessage: String?) : CardCarouselEvent, ErrorEvent
}

internal class CardCarouselViewModel(
    reducer: CardCarouselReducer,
    loadCardsInteractor: LoadCardsInteractor?,
) : BaseViewModel<CardCarouselState, CardCarouselEvent>(
    reducer = reducer, interactors = setOfNotNull(loadCardsInteractor)
) {

    init {
        loadCards()
    }

    private fun loadCards() {
        sendEvent(event = CardCarouselEvent.LoadCards)
    }
}

/** This function is used only for compose preview. */
@RestrictTo(RestrictTo.Scope.TESTS)
internal fun getDummyCardCarouselViewModel(): CardCarouselViewModel {
    return CardCarouselViewModel(reducer = CardCarouselReducer(), loadCardsInteractor = null)
}