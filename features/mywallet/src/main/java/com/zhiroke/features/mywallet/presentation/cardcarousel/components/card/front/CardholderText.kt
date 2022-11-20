package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
internal fun CardholderText(modifier: Modifier = Modifier, cardholder: String) {
    Text(modifier = modifier, text = cardholder)
}