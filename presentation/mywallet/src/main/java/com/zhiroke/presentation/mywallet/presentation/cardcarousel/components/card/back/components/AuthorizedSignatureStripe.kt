package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.back.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.color.Gray
import com.zhiroke.core.theme.demensions.dp_24


@Composable
internal fun AuthorizedSignatureStripe(modifier: Modifier = Modifier) {

    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(dp_24)
            .background(Gray)
    )
}