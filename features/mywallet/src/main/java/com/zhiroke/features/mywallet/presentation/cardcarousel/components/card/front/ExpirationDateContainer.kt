package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.utils.MaterialTypography
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun ExpirationDateContainer(modifier: Modifier = Modifier, expirationDate: String) {

    CardTextContainer(modifier = modifier) {

        ExpirationDateText(
            modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
            expirationDate = expirationDate
        )
    }
}


@Composable
private fun ExpirationDateText(modifier: Modifier = Modifier, expirationDate: String) {

    Text(
        modifier = modifier,
        text = expirationDate,
        style = MaterialTypography.titleMedium
    )
}