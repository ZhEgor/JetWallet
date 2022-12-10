package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun VerificationNumberContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    CardTextContainer(modifier = modifier, content = content)
}

@Composable
internal fun VerificationNumberText(modifier: Modifier = Modifier, number: String) {

    Text(modifier = modifier, text = number)
}