package com.zhiroke.core.components.popup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.theme.demensions.*
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes

@Composable
internal fun Dash() {

    Spacer(
        modifier = Modifier
            .padding(vertical = dp_16)
            .size(width = dp_64, height = dp_6)
            .clip(MaterialShapes.extraSmall)
            .background(color = MaterialColor.secondary)
    )
}

@Preview
@Composable
fun DashPreview() {
    Dash()
}