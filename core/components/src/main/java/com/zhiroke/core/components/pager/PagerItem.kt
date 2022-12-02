package com.zhiroke.core.components.pager

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScope.PagerItemWithTransition(
    modifier: Modifier = Modifier,
    page: Int,
    content: @Composable BoxScope.() -> Unit
) {

    StatelessPagerItem(
        modifier = modifier.graphicsLayer {

            val pageOffset = calculateCurrentOffsetForPage(page = page).absoluteValue

            lerp(
                start = 0.85f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            ).also { scale ->
                scaleX = scale
                scaleY = scale
            }

            alpha = lerp(
                start = 0.5f,
                stop = 1f,
                fraction = 1f - pageOffset.coerceIn(0f, 1f)
            )
        },
        content = content
    )
}

@Composable
private fun StatelessPagerItem(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {

    Box(modifier = modifier) {
        content.invoke(this)
    }
}
