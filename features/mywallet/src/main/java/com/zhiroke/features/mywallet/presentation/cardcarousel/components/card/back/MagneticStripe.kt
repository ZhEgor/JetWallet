package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.color.Black
import com.zhiroke.core.theme.demensions.dp_48


@Composable
internal fun MagneticStripe(modifier: Modifier = Modifier) {

    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(dp_48)
            .background(Black)
    )
}