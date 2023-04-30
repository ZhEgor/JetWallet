package com.zhiroke.presentation.auth.presentation.auth.components.popups.components

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.presentation.auth.R
import com.zhiroke.presentation.auth.presentation.auth.components.buttons.TextButtonWithTimer

@Composable
internal fun DropDataButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val colors = ButtonDefaults.buttonColors(
        contentColor = MaterialColor.onError,
        containerColor = MaterialColor.error,
        disabledContentColor = MaterialColor.onError.copy(alpha = 0.58f),
        disabledContainerColor = MaterialColor.error.copy(alpha = 0.22f)
    )

    TextButtonWithTimer(
        modifier = modifier,
        text = stringResource(id = R.string.button_drop_data_confirm),
        durationInSeconds = 15,
        colors = colors,
        onClick = onClick
    )
}

