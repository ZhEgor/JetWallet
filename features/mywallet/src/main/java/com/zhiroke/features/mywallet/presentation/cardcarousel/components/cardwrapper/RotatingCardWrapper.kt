package com.zhiroke.features.mywallet.presentation.cardcarousel.components.cardwrapper

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import com.zhiroke.core.components.cardwrapper.StatelessCardWrapper


/**
 * Stateful rotating card
 */
@Composable
fun RotatingCardWrapper(
    modifier: Modifier = Modifier,
    initialIsFrontSide: Boolean = true,
    onLongPress: ((isFront: Boolean) -> Unit)? = null,
    cardContent: @Composable (isFrontSide: Boolean) -> Unit
) {
    var isFrontSide by rememberSaveable(initialIsFrontSide) { mutableStateOf(initialIsFrontSide) }
    val rotateX by remember { mutableStateOf(0f) }
    var rotateY by remember { mutableStateOf(0f) }

    val onDoubleTap: (Offset) -> Unit = {
        isFrontSide = !isFrontSide
    }

    rotateY = animateFloatAsState(
        targetValue = if (isFrontSide) 0f else 180f,
        animationSpec = tween(durationMillis = 1500),
    ).value

    StatelessCardWrapper(
        modifier = modifier,
        rotateX = rotateX,
        rotateY = rotateY,
        onLongPress = {
            onLongPress?.invoke(isFrontSide)
        },
        onDoubleTap = onDoubleTap,
        cardContent = {
            cardContent.invoke(isFrontSide = rotateY <= 90)
        }
    )
}
