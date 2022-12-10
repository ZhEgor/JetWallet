package com.zhiroke.core.components.buttons

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes


@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialColor.secondary,
        contentColor = MaterialColor.onSecondary
    )

    Button(
        modifier = modifier,
        colors = buttonColors,
        shape = MaterialShapes.small,
        onClick = onClick,
        enabled = enabled,
    ) {

        Text(text = text)
    }
}

@Composable
fun ButtonText(
    modifier: Modifier = Modifier,
    @StringRes textResId: Int,
    onClick: () -> Unit,
    enabled: Boolean = true,
) {

    ButtonText(
        modifier = modifier,
        text = stringResource(id = textResId),
        onClick = onClick,
        enabled = enabled,
    )
}

@Preview
@Composable
private fun ButtonTextPreview() {
    ButtonText(text = "Create") {

    }
}