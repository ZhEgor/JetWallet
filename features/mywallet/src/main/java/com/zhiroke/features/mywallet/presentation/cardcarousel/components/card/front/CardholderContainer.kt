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
internal fun CardholderContainer(modifier: Modifier = Modifier, cardholder: String) {

    CardTextContainer(modifier = modifier) {

        CardholderText(
            modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
            cardholder = cardholder
        )
    }
}

@Composable
private fun CardholderText(modifier: Modifier = Modifier, cardholder: String) {

    Text(
        modifier = modifier,
        text = cardholder,
        style = MaterialTypography.titleMedium
    )
}