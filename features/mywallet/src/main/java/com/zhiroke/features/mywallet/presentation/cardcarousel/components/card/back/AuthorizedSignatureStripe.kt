package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.zhiroke.core.theme.demensions.dp_12


@Composable
internal fun AuthorizedSignatureStripe(modifier: Modifier = Modifier) {

    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(dp_12)
            .background(Color.Gray)
    )
}