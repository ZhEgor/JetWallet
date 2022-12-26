package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardNumberContainer
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardholderContainer
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.ExpirationDateContainer
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.SkinContainer


@Composable
internal fun StatelessFrontSideCard(
    modifier: Modifier = Modifier,
    cardNumberContent: @Composable () -> Unit,
    expirationDateContent: @Composable () -> Unit,
    cardholderContent: @Composable () -> Unit,
    skinContent: (@Composable () -> Unit)? = null
) {

    Box(modifier = modifier) {

        skinContent?.let { content ->
            SkinContainer(content = content)
        }

        CardNumberContainer(
            modifier = Modifier.align(alignment = Alignment.Center),
            content = cardNumberContent
        )

        ExpirationDateContainer(
            modifier = Modifier.align(alignment = Alignment.BottomStart),
            content = expirationDateContent
        )

        CardholderContainer(
            modifier = Modifier.align(alignment = Alignment.BottomEnd),
            content = cardholderContent
        )
    }
}