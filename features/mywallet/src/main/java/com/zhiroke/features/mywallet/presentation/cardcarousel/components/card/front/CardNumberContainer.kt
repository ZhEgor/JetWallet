package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun CardNumberContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    CardTextContainer(modifier = modifier, content = content)
}

@Composable
internal fun CardNumberText(modifier: Modifier = Modifier, number: String) {

    Text(
        modifier = modifier,
        text = number.toCardNumberFormat(),
    )
}

private fun String.toCardNumberFormat(): String {
    val multiplier = 4
    return this.mapIndexed { index, char ->
        if (index % multiplier == 0 && index != 0) "  $char" else char
    }.joinToString("")
}