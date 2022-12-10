package com.zhiroke.features.mywallet.presentation.cardcarousel.interactors

import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepository
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselEvent
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselState


internal class SaveCardInteractor(private val bankCardRepository: BankCardRepository) :
    SuspendableInteractor<CardCarouselState, CardCarouselEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: CardCarouselState, event: CardCarouselEvent) = tryCatch(
        onFailureHandler = { e ->
            CardCarouselEvent.FailedToLoadCards(errorMessage = e.message)
        }
    ) {
        val rawBankCard = (event as CardCarouselEvent.SaveCard).bankCard

        val bankCard = rawBankCard.copy(cardholderName = rawBankCard.cardholderName.trim())

        bankCardRepository.updateBankCard(bankCard = bankCard)
        CardCarouselEvent.SavedCardSuccessfully
    }

    override fun canHandle(event: CardCarouselEvent) = event is CardCarouselEvent.SaveCard
}