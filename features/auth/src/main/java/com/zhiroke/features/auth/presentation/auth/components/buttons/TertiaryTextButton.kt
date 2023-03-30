package com.zhiroke.features.auth.presentation.auth.components.buttons

import androidx.annotation.StringRes
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import com.zhiroke.core.theme.utils.MaterialColor

@Composable
fun TertiaryTextButton(
    modifier: Modifier = Modifier,
    @StringRes resId: Int,
    fontSize: TextUnit,
    enabled: Boolean = true,
    onClick: () -> Unit
) {

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialColor.tertiary,
        contentColor = MaterialColor.onTertiary,
        disabledContainerColor = MaterialColor.tertiary.copy(alpha = 0.12f),
        disabledContentColor = MaterialColor.onTertiary.copy(alpha = 0.38f)
    )

    TextButton(
        modifier = modifier,
        colors = buttonColors,
        enabled = enabled,
        onClick = onClick,
    ) {

        Text(text = stringResource(id = resId), fontSize = fontSize)
    }
}