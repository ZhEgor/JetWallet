package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_16

@Composable
internal fun BoxScope.ToolsContainer(modifier: Modifier = Modifier, toolsContent: @Composable (RowScope.() -> Unit)? = null) {

    Row(
        modifier = modifier
            .wrapContentSize()
            .align(alignment = Alignment.TopEnd)
            .padding(top = dp_16, end = dp_16),
    ) {

        toolsContent?.invoke(this@Row)
    }
}