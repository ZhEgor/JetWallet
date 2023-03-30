package com.zhiroke.features.auth.presentation.auth.components.passwordfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_32
import com.zhiroke.core.theme.demensions.dp_64

@Composable
internal fun PINField(
    modifier: Modifier,
    pin: String,
    visible: Boolean
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dp_16)
    ) {

        repeat(4) { index ->
            PINCharacterItem(
                modifier = Modifier.size(width = dp_32, height = dp_64),
                char = pin.getOrNull(index),
                textFontSize = MaterialTheme.typography.titleLarge.fontSize,
                visible = visible
            )
        }
    }
}