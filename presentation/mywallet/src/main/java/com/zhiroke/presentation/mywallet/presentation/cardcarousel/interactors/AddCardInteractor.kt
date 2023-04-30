package com.zhiroke.presentation.mywallet.presentation.cardcarousel.interactors

import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.domain.models.BankCard
import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepository
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.CardCarouselEvent
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.CardCarouselState
import java.util.*


internal class AddCardInteractor(private val bankCardRepository: BankCardRepository) :
    SuspendableInteractor<CardCarouselState, CardCarouselEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: CardCarouselState, event: CardCarouselEvent) = tryCatch(
        onFailureHandler = { e ->
            CardCarouselEvent.FailedToLoadCards(errorMessage = e.message)
        }
    ) {
        val rawBankCard = (event as CardCarouselEvent.AddCard).bankCard

        val bankCard = BankCard(
            id = UUID.randomUUID().toString(),
            number = rawBankCard.number,
            cardholderName = rawBankCard.cardholderName.trim(),
            expirationDate = rawBankCard.expirationDate,
            verificationNumber = rawBankCard.verificationNumber,
            skinUri = rawBankCard.skinUri
        )

        bankCardRepository.addBankCard(bankCard = bankCard)
        CardCarouselEvent.AddedCardSuccessfully
    }

    override fun canHandle(event: CardCarouselEvent) = event is CardCarouselEvent.AddCard
}