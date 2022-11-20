package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
internal fun CardNumberText(modifier: Modifier = Modifier,number: String) {
    Text(modifier = modifier, text = number)
}