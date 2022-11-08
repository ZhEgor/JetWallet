package com.zhiroke.features.mywallet.presentation.interactors

import com.zhiroke.core.common.base.BaseInteractor
import com.zhiroke.core.common.base.error.ErrorInteractor
import com.zhiroke.core.components.utils.StableList
import com.zhiroke.core.components.utils.stableListOf
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.CardCarouselEvent
import com.zhiroke.features.mywallet.presentation.CardCarouselState

internal class LoadCardsInteractor : BaseInteractor<CardCarouselState, CardCarouselEvent>, ErrorInteractor {

    override suspend fun invoke(state: CardCarouselState, event: CardCarouselEvent) = tryCatch(
        onFailureHandler = { e ->
            CardCarouselEvent.FailedToLoadCards(errorMessage = e.message)
        }
    ) {
        CardCarouselEvent.LoadedCards(cards = getCardsFromDummyRepo())
    }

    override fun canHandle(event: CardCarouselEvent) = event is CardCarouselEvent.LoadCards

    private suspend fun getCardsFromDummyRepo(): StableList<BankCard> = stableListOf(
        BankCard(
            number = "1111123412341234",
            cardholderName = "Someone1",
            expirationDate = "12/08",
            verificationNumber = "892",
        ),
        BankCard(
            number = "2222123412341234",
            cardholderName = "Someone2",
            expirationDate = "12/08",
            verificationNumber = "892",
        ),
        BankCard(
            number = "3333123412341234",
            cardholderName = "Someone2",
            expirationDate = "12/08",
            verificationNumber = "892",
        ),
    )
}