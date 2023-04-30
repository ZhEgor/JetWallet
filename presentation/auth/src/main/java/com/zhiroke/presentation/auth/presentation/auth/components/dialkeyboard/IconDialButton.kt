package com.zhiroke.presentation.auth.presentation.auth.components.dialkeyboard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.zhiroke.core.common.utils.rememberLambda
import com.zhiroke.core.theme.demensions.dp_0
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.presentation.auth.presentation.auth.interactors.PINKeyboardCommand

@Composable
internal fun IconDialButton(
    modifier: Modifier = Modifier,
    @DrawableRes resId: Int,
    iconSize: Dp,
    command: PINKeyboardCommand,
    onClick: (PINKeyboardCommand) -> Unit
) {

    val onButtonClick = rememberLambda {
        onClick.invoke(command)
    }

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialColor.secondary,
        contentColor = MaterialColor.onSecondary
    )

    Button(
        modifier = modifier,
        colors = buttonColors,
        shape = CircleShape,
        onClick = onButtonClick,
        contentPadding = PaddingValues(dp_0)
    ) {

        Icon(
            modifier = Modifier.size(iconSize),
            painter = painterResource(id = resId),
            contentDescription = null // TODO change me
        )
    }
}