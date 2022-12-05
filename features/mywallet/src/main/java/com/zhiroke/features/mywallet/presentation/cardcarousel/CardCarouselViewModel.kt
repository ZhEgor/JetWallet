package com.zhiroke.features.mywallet.presentation.cardcarousel

import androidx.annotation.RestrictTo
import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.BaseState
import com.zhiroke.core.common.base.BaseViewModel
import com.zhiroke.core.common.base.error.ErrorEvent
import com.zhiroke.core.common.base.error.ErrorState
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.components.utils.StableList
import com.zhiroke.core.components.utils.emptyStableList
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.AddCardInteractor
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.LoadCardsInteractor
import com.zhiroke.features.mywallet.presentation.cardcarousel.popups.RawBankCard


internal data class CardCarouselState(
    override val errorMessage: String?,
    val areCardsLoading: Boolean,
    val cards: StableList<BankCard>,
    val createCardPopUp: PopUpState
) : BaseState, ErrorState {

    companion object {
        fun initialState() = CardCarouselState(
            areCardsLoading = false,
            errorMessage = null,
            cards = emptyStableList(),
            createCardPopUp = PopUpState.initialState()
        )
    }
}

internal sealed interface CardCarouselEvent : BaseEvent {
    object LoadCards : CardCarouselEvent
    class LoadedCards(val cards: StableList<BankCard>) : CardCarouselEvent
    class FailedToLoadCards(override val errorMessage: String?) : CardCarouselEvent, ErrorEvent
    class ChangeStateOfCreatePopUp(val show: Boolean) : CardCarouselEvent
    class AddCard(val rawBankCard: RawBankCard) : CardCarouselEvent
    object AddedCardSuccessfully : CardCarouselEvent
}

internal class CardCarouselViewModel(
    reducer: CardCarouselReducer,
    loadCardsInteractor: LoadCardsInteractor?,
    addCardInteractor: AddCardInteractor?,
) : BaseViewModel<CardCarouselState, CardCarouselEvent>(
    reducer = reducer, interactors = setOfNotNull(loadCardsInteractor, addCardInteractor)
) {

    init {
        loadCards()
    }

    private fun loadCards() = sendEvent(event = CardCarouselEvent.LoadCards)

    fun showPopUpCreate() = sendEvent(event = CardCarouselEvent.ChangeStateOfCreatePopUp(show = true))

    fun hidePopUpCreate() = sendEvent(event = CardCarouselEvent.ChangeStateOfCreatePopUp(show = false))

    fun addBankCard(rawBankCard: RawBankCard) {
        loadCards()
        sendEvent(event = CardCarouselEvent.AddCard(rawBankCard = rawBankCard))
    }
}

/** This function is used only for compose preview. */
@RestrictTo(RestrictTo.Scope.TESTS)
internal fun getDummyCardCarouselViewModel(): CardCarouselViewModel {
    return CardCarouselViewModel(reducer = CardCarouselReducer(), loadCardsInteractor = null, addCardInteractor = null)
}