package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun CardNumberContainer(modifier: Modifier = Modifier, number: String) {

    CardTextContainer(modifier = modifier) {

        CardNumberText(
            modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
            number = number
        )
    }
}

@Composable
private fun CardNumberText(modifier: Modifier = Modifier, number: String) {

    Text(
        modifier = modifier,
        text = number.toCardNumberFormat(),
//        style = MaterialTypography.headlineSmall.copy(fontWeight = FontWeight.Medium)
    )
}

private fun String.toCardNumberFormat(): String {
    val multiplier = 4
    return this.mapIndexed { index, char ->
        if (index % multiplier == 0 && index != 0) "  $char" else char
    }.joinToString("")
}