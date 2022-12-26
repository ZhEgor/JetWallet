package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
internal fun SkinContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    Box(modifier = modifier.fillMaxSize()) {

        content.invoke()
    }
}