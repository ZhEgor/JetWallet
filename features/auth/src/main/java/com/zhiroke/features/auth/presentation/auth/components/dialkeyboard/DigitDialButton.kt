package com.zhiroke.features.auth.presentation.auth.components.dialkeyboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import com.zhiroke.core.common.utils.rememberLambda
import com.zhiroke.core.theme.demensions.dp_0
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.features.auth.presentation.auth.interactors.PINKeyboardCommand

@Composable
internal fun DigitDialButton(
    modifier: Modifier = Modifier,
    digit: String,
    fontSize: TextUnit,
    onClick: (PINKeyboardCommand) -> Unit
) {

    val onButtonClick = rememberLambda {
        onClick.invoke(PINKeyboardCommand.WriteCharacter(character = digit))
    }

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialColor.primary,
        contentColor = MaterialColor.onPrimary
    )

    Button(
        modifier = modifier,
        colors = buttonColors,
        shape = CircleShape,
        onClick = onButtonClick,
        contentPadding = PaddingValues(dp_0)
    ) {

        Text(
            text = digit,
            fontSize = fontSize
        )
    }
}