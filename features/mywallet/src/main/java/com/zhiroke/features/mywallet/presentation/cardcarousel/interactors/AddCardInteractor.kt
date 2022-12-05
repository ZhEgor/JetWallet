package com.zhiroke.features.mywallet.presentation.cardcarousel.interactors

import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.domain.models.BankCard
import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepository
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselEvent
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselState
import java.util.UUID


internal class AddCardInteractor(private val bankCardRepository: BankCardRepository) :
    SuspendableInteractor<CardCarouselState, CardCarouselEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: CardCarouselState, event: CardCarouselEvent) = tryCatch(
        onFailureHandler = { e ->
            CardCarouselEvent.FailedToLoadCards(errorMessage = e.message)
        }
    ) {
        val rawBankCard = (event as CardCarouselEvent.AddCard).rawBankCard

        val bankCard = BankCard(
            id = UUID.randomUUID().toString(),
            number = rawBankCard.number,
            cardholderName = rawBankCard.cardholderName,
            expirationDate = rawBankCard.expirationDate,
            verificationNumber = rawBankCard.verificationNumber
        )

        bankCardRepository.addBankCard(bankCard = bankCard)
        CardCarouselEvent.AddedCardSuccessfully
    }

    override fun canHandle(event: CardCarouselEvent) = event is CardCarouselEvent.AddCard
}