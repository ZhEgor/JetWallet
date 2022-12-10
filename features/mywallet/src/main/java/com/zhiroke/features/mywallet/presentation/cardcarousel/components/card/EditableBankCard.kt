package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card

import androidx.compose.runtime.Composable
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.ImmutableBackSideCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.ImmutableFrontSideCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.cardwrapper.RotatingCardWrapper


@Composable
internal fun EditableBankCard(bankCard: BankCard, isFrontSide: Boolean) {

    RotatingCardWrapper(initialIsFrontSide = isFrontSide) { _isFrontSide ->
        if (_isFrontSide) {
            ImmutableFrontSideCard(bankCard = bankCard)
        } else {
            ImmutableBackSideCard(bankCard = bankCard)
        }
    }
}
