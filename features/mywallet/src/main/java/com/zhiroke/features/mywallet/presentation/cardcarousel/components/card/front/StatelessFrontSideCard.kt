package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardNumberContainer
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardholderContainer
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.ExpirationDateContainer


@Composable
internal fun StatelessFrontSideCard(
    modifier: Modifier = Modifier,
    cardNumberContent: @Composable () -> Unit,
    expirationDateContainer: @Composable () -> Unit,
    cardholderContainer: @Composable () -> Unit,
) {

    Box(modifier = modifier) {

        CardNumberContainer(
            modifier = Modifier.align(alignment = Alignment.Center),
            content = cardNumberContent
        )

        ExpirationDateContainer(
            modifier = Modifier.align(alignment = Alignment.BottomStart),
            content = expirationDateContainer
        )

        CardholderContainer(
            modifier = Modifier.align(alignment = Alignment.BottomEnd),
            content = cardholderContainer
        )
    }
}