package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.common

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes

@Composable
fun CardTextContainer(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    Surface(
        modifier = modifier.clip(shape = MaterialShapes.small),
        color = MaterialColor.primary,
        content = content
    )
}