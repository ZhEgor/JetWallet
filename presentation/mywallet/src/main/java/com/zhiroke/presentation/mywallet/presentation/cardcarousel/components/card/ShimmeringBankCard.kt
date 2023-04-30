package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_32
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.back.ShimmeringBackSideCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.front.ShimmeringFrontSideCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.cardwrapper.RotatingCardWrapper


@Composable
internal fun ShimmeringBankCard() {

    RotatingCardWrapper(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = dp_16, vertical = dp_32),
    ) { isFrontSide ->

        if (isFrontSide) {
            ShimmeringFrontSideCard()
        } else {
            ShimmeringBackSideCard()
        }
    }
}