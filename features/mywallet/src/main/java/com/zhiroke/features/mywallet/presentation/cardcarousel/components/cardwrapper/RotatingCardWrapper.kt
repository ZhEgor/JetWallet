package com.zhiroke.features.mywallet.presentation.cardcarousel.components.cardwrapper

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
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
    var isFrontSide by remember { mutableStateOf(initialIsFrontSide) }
    var rotateX by remember { mutableStateOf(0f) }
    var rotateY by remember { mutableStateOf(0f) }
    var rotateYTo by remember { mutableStateOf(rotateY) }
    var isRotating by remember { mutableStateOf(false) }

    val onDoubleTap: (Offset) -> Unit = {
        isFrontSide = !isFrontSide
        isRotating = true
        rotateYTo = if (isFrontSide) 0f else 180f
    }
    val onDrag: (PointerInputChange, Offset) -> Unit = { _, dragAmount ->
        val multiplier = if (isFrontSide) 0f else 180f
        if (!isRotating) {
            when {
                dragAmount.x > 0 && rotateY < multiplier + 3f -> rotateY += 0.5f
                dragAmount.x < 0 && rotateY > multiplier - 3f -> rotateY -= 0.5f
                dragAmount.y > 0 && rotateX > -6f -> rotateX -= 0.5f
                dragAmount.y < 0 && rotateX < 6f -> rotateX += 0.5f
                else -> {
                    dragAmount.x > multiplier + 0 && rotateY < multiplier + 3f
                }
            }
            rotateYTo = rotateY
        }
    }

    animateFloatAsState(
        targetValue = rotateYTo,
        animationSpec = tween(durationMillis = 1500),
        finishedListener = { isRotating = false }
    ).value.let { newY ->
        if (isRotating) {
            rotateY = newY
        }
    }

    StatelessCardWrapper(
        modifier = modifier,
        rotateX = rotateX,
        rotateY = rotateY,
        onDrag = onDrag,
        onLongPress = {
            onLongPress?.invoke(isFrontSide)
        },
        onDoubleTap = onDoubleTap,
        cardContent = {
            cardContent.invoke(isFrontSide = rotateY <= 90)
        }
    )
}
