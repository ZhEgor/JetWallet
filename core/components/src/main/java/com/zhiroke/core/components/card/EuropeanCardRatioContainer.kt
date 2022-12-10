package com.zhiroke.core.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EuropeanCardRatioContainer(content: @Composable BoxScope.() -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 1.647f),
        content = content
    )
}