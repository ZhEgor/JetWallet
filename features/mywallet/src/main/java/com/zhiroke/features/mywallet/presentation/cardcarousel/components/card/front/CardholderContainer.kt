package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.utils.MaterialTypography
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun CardholderContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    CardTextContainer(modifier = modifier, content = content)
}

@Composable
internal fun CardholderText(modifier: Modifier = Modifier, cardholder: String) {

    Text(
        modifier = modifier,
        text = cardholder,
        style = MaterialTypography.titleMedium
    )
}