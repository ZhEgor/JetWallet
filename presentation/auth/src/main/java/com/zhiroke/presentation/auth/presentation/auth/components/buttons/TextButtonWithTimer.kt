package com.zhiroke.presentation.auth.presentation.auth.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.zhiroke.core.components.R
import kotlinx.coroutines.delay

@Composable
fun TextButtonWithTimer(
    modifier: Modifier = Modifier,
    text: String,
    durationInSeconds: Int,
    onClick: () -> Unit,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    val context = LocalContext.current
    var enabled by remember { mutableStateOf(false) }
    var textWithTimer by remember { mutableStateOf(text) }

    LaunchedEffect(key1 = Unit) {
        for (secondsLeft in durationInSeconds downTo 0) {
            if (secondsLeft != 0) {
                textWithTimer = text + context.getString(R.string.placeholder_timer, secondsLeft)
                enabled = false
            } else {
                textWithTimer = text
                enabled = true
            }
            delay(1000)
        }
    }

    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {

        Text(
            text = textWithTimer,
            textAlign = TextAlign.Center
        )
    }
}