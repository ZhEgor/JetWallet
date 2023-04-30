package com.zhiroke.presentation.mywallet.presentation.cardcarousel

import com.zhiroke.core.common.base.BaseState
import com.zhiroke.core.common.base.error.ErrorState
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.components.utils.StableList
import com.zhiroke.domain.models.BankCard


internal data class CardCarouselState(
    override val errorMessage: String?,
    val cardsState: CardsState,
    val addCardPopUp: PopUpState,
    val editCardPopUp: PopUpState,
    val cardToEdit: BankCard?
) : BaseState, ErrorState {

    companion object {

        fun initialState() = CardCarouselState(
            errorMessage = null,
            cardsState = CardsState.Loading,
            addCardPopUp = PopUpState.initialState(),
            editCardPopUp = PopUpState.initialState(),
            cardToEdit = null
        )
    }
}

internal sealed interface CardsState {
    object Loading : CardsState
    class Loaded(val cards: StableList<BankCard>) : CardsState
    object Empty : CardsState
}