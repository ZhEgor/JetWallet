package com.zhiroke.features.mywallet.presentation.cardcarousel.components.button

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_0
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_24
import com.zhiroke.core.theme.demensions.dp_96
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes


@Composable
internal fun BoxScope.AddCardButton(onClick: () -> Unit) {

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialColor.secondary,
        contentColor = MaterialColor.onSecondary
    )

    Button(
        modifier = Modifier
            .size(dp_96)
            .align(Alignment.BottomEnd)
            .padding(dp_16),
        colors = buttonColors,
        shape = MaterialShapes.small,
        onClick = onClick,
        contentPadding = PaddingValues(dp_0)
    ) {

        Icon(
            modifier = Modifier.size(dp_24),
            imageVector = Icons.Rounded.Add,
            contentDescription = null
        )
    }
}