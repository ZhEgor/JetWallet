package com.zhiroke.features.mywallet.presentation.cardcarousel

import androidx.annotation.RestrictTo
import com.zhiroke.core.common.base.BaseViewModel
import com.zhiroke.core.common.coroutines.AppDispatchersProvider
import com.zhiroke.core.common.coroutines.DispatchersProvider
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.AddCardInteractor
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.DeleteCardInteractor
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.LoadCardsInteractor
import com.zhiroke.features.mywallet.presentation.cardcarousel.interactors.SaveCardInteractor


internal class CardCarouselViewModel(
    reducer: CardCarouselReducer,
    dispatchers: DispatchersProvider,
    loadCardsInteractor: LoadCardsInteractor?,
    addCardInteractor: AddCardInteractor?,
    saveCardInteractor: SaveCardInteractor?,
    deleteCardInteractor: DeleteCardInteractor?,
) : BaseViewModel<CardCarouselState, CardCarouselEvent>(
    dispatchers = dispatchers,
    reducer = reducer,
    interactors = setOfNotNull(loadCardsInteractor, addCardInteractor, saveCardInteractor, deleteCardInteractor)
) {

    init {
        loadCards()
    }

    private fun loadCards() = sendEvent(event = CardCarouselEvent.LoadCards)

    fun showAddPopUp() = sendEvent(event = CardCarouselEvent.ChangeStateOfAddPopUp(show = true))

    fun hideAddPopUp() = sendEvent(event = CardCarouselEvent.ChangeStateOfAddPopUp(show = false))

    fun showEditPopUp(bankCard: BankCard) = sendEvent(event = CardCarouselEvent.ChangeStateOfEditPopUp(show = true, cardToEdit = bankCard))

    fun hideEditPopUp() = sendEvent(event = CardCarouselEvent.ChangeStateOfEditPopUp(show = false))

    fun addBankCard(bankCard: BankCard) = sendEvent(event = CardCarouselEvent.AddCard(bankCard = bankCard))

    fun saveBankCard(bankCard: BankCard) = sendEvent(event = CardCarouselEvent.SaveCard(bankCard = bankCard))

    fun deleteBankCard(bankCard: BankCard) = sendEvent(event = CardCarouselEvent.DeleteCard(bankCard = bankCard))
}

/** This function is used only for compose preview. */
@RestrictTo(RestrictTo.Scope.TESTS)
internal fun getDummyCardCarouselViewModel(): CardCarouselViewModel {
    return CardCarouselViewModel(
        dispatchers = AppDispatchersProvider(),
        reducer = CardCarouselReducer(),
        loadCardsInteractor = null,
        addCardInteractor = null,
        saveCardInteractor = null,
        deleteCardInteractor = null
    )
}