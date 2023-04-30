package com.zhiroke.presentation.mywallet.presentation.cardcarousel

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.error.ErrorEvent
import com.zhiroke.core.components.utils.StableList
import com.zhiroke.domain.models.BankCard


internal sealed interface CardCarouselEvent : BaseEvent {
    object LoadCards : CardCarouselEvent
    class LoadedCards(val cards: StableList<BankCard>) : CardCarouselEvent
    class FailedToLoadCards(override val errorMessage: String?) : CardCarouselEvent, ErrorEvent
    class ChangeStateOfAddPopUp(val show: Boolean) : CardCarouselEvent
    class ChangeStateOfEditPopUp(val show: Boolean, val cardToEdit: BankCard? = null) : CardCarouselEvent
    class AddCard(val bankCard: BankCard) : CardCarouselEvent
    object AddedCardSuccessfully : CardCarouselEvent
    class SaveCard(val bankCard: BankCard) : CardCarouselEvent
    object SavedCardSuccessfully : CardCarouselEvent
    class DeleteCard(val bankCard: BankCard) : CardCarouselEvent
    object DeletedCardSuccessfully : CardCarouselEvent
}
