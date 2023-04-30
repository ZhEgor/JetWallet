package com.zhiroke.presentation.mywallet.presentation.cardcarousel.interactors

import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepository
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.CardCarouselEvent
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.CardCarouselState


internal class DeleteCardInteractor(private val bankCardRepository: BankCardRepository) :
    SuspendableInteractor<CardCarouselState, CardCarouselEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: CardCarouselState, event: CardCarouselEvent) = tryCatch(
        onFailureHandler = { e ->
            CardCarouselEvent.FailedToLoadCards(errorMessage = e.message)
        }
    ) {
        val bankCard = (event as CardCarouselEvent.DeleteCard).bankCard

        bankCardRepository.deleteBankCard(bankCard = bankCard)
        CardCarouselEvent.DeletedCardSuccessfully
    }

    override fun canHandle(event: CardCarouselEvent) = event is CardCarouselEvent.DeleteCard
}