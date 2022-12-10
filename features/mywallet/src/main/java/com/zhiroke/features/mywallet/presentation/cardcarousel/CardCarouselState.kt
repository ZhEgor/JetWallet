package com.zhiroke.features.mywallet.presentation.cardcarousel

import com.zhiroke.core.common.base.BaseState
import com.zhiroke.core.common.base.error.ErrorState
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.components.utils.StableList
import com.zhiroke.core.components.utils.emptyStableList
import com.zhiroke.domain.models.BankCard


internal data class CardCarouselState(
    override val errorMessage: String?,
    val areCardsLoading: Boolean,
    val cards: StableList<BankCard>,
    val addCardPopUp: PopUpState,
    val editCardPopUp: PopUpState,
    val cardToEdit: BankCard?
) : BaseState, ErrorState {

    companion object {

        fun initialState() = CardCarouselState(
            areCardsLoading = false,
            errorMessage = null,
            cards = emptyStableList(),
            addCardPopUp = PopUpState.initialState(),
            editCardPopUp = PopUpState.initialState(),
            cardToEdit = null
        )
    }
}