package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.front.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.utils.MaterialTypography
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun ExpirationDateContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    CardTextContainer(modifier = modifier, content = content)
}

@Composable
internal fun ExpirationDateText(modifier: Modifier = Modifier, expirationDate: String) {

    Text(
        modifier = modifier,
        text = expirationDate.toExpirationDateFormat(),
        style = MaterialTypography.titleMedium
    )
}

private fun String.toExpirationDateFormat(): String {
    return this.mapIndexed { index, char ->
        if (index == 2) "/$char" else char
    }.joinToString("")
}