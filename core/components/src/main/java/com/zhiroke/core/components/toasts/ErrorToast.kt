package com.zhiroke.core.components.toasts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_360
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.demensions.dp_96
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes

@Composable
fun ErrorToast(text: String?) {
    text ?: return
    ErrorContainer {
        ErrorBlock(modifier = Modifier.align(Alignment.BottomCenter), text = text)
    }
}

@Composable
private fun ErrorBlock(modifier: Modifier = Modifier, text: String) {
    Surface(
        modifier = modifier
            .width(width = dp_360)
            .defaultMinSize(minHeight = dp_96)
            .padding(all = dp_16),
        shape = MaterialShapes.large,
        color = MaterialColor.errorContainer,
        contentColor = MaterialColor.onErrorContainer
    ) {
        Text(
            modifier = Modifier.padding(all = dp_8),
            text = text,
        )
    }
}
@Composable
private fun ErrorContainer(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        content.invoke(this)
    }
}

@Preview
@Composable
fun ErrorPreview() {
    ErrorContainer {
        ErrorBlock(modifier = Modifier.align(Alignment.BottomCenter), text = "too many attempts!")
    }
}