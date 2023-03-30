package com.zhiroke.features.auth.presentation.auth.components.dialkeyboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.theme.demensions.dp_24
import com.zhiroke.core.theme.demensions.dp_48
import com.zhiroke.core.theme.demensions.dp_80
import com.zhiroke.features.auth.R
import com.zhiroke.features.auth.presentation.auth.interactors.PINKeyboardCommand

@Composable
internal fun DialKeyboardBlock(
    modifier: Modifier,
    biometricAuthSupported: Boolean,
    onCommand: (PINKeyboardCommand) -> Unit,
    onBioClick: (PINKeyboardCommand) -> Unit
) {

    val padding = dp_24
    val buttonSize = dp_80
    val fontSize = MaterialTheme.typography.headlineSmall.fontSize

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.spacedBy(padding)) {

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "1",
                fontSize = fontSize,
                onClick = onCommand
            )

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "2",
                fontSize = fontSize,
                onClick = onCommand
            )

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "3",
                fontSize = fontSize,
                onClick = onCommand
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(padding)) {

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "4",
                fontSize = fontSize,
                onClick = onCommand
            )

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "5",
                fontSize = fontSize,
                onClick = onCommand
            )

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "6",
                fontSize = fontSize,
                onClick = onCommand
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(padding)) {

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "7",
                fontSize = fontSize,
                onClick = onCommand
            )

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "8",
                fontSize = fontSize,
                onClick = onCommand
            )

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "9",
                fontSize = fontSize,
                onClick = onCommand
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(padding)) {

            if (biometricAuthSupported) {
                IconDialButton(
                    modifier = Modifier.size(buttonSize),
                    resId = R.drawable.ic_fingerprint,
                    iconSize = dp_48,
                    command = PINKeyboardCommand.AuthWithBio,
                    onClick = onBioClick
                )
            } else {
                Spacer(modifier = Modifier.size(buttonSize))
            }

            DigitDialButton(
                modifier = Modifier.size(buttonSize),
                digit = "0",
                fontSize = fontSize,
                onClick = onCommand
            )

            IconDialButton(
                modifier = Modifier.size(buttonSize),
                resId = R.drawable.ic_backspace,
                iconSize = dp_24,
                command = PINKeyboardCommand.RemoveCharacter,
                onClick = onCommand
            )
        }
    }
}

@Preview
@Composable
private fun DialKeyboardBlockPreview() {

    DialKeyboardBlock(modifier = Modifier.fillMaxSize(), true, {}, {})
}
