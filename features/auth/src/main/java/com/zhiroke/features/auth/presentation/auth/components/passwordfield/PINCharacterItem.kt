package com.zhiroke.features.auth.presentation.auth.components.passwordfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.TextUnit
import com.zhiroke.core.theme.demensions.dp_64
import com.zhiroke.core.theme.utils.MaterialColor

@Composable
internal fun PINCharacterItem(
    modifier: Modifier,
    char: Char?,
    textFontSize: TextUnit,
    visible: Boolean,
) {

    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(dp_64))
            .background(color = MaterialColor.primary),
        color = MaterialColor.primaryContainer
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (visible) {
                Text(
                    text = char?.toString() ?: "",
                    fontSize = textFontSize
                )
            } else if (char != null) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = MaterialColor.onPrimaryContainer)
                )
            }

        }
    }
}