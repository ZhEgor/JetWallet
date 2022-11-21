package com.zhiroke.features.mywallet.presentation.cardcarousel.components.cardwrapper

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import com.zhiroke.core.components.cardwrapper.StatelessCardWrapper
import com.zhiroke.features.mywallet.utils.roundUpToMultipleOf


/**
 * Stateful rotating card
 */
@Composable
fun RotatingCardWrapper( // ToDo: finalize
    modifier: Modifier = Modifier,
    initialIsFrontSide: Boolean = true,
    onLongPress: ((isFront: Boolean) -> Unit)? = null,
    cardContent: @Composable (isFrontSide: Boolean) -> Unit
) {
    var isFrontSide by remember { mutableStateOf(initialIsFrontSide) }
    var isRotating by remember { mutableStateOf(false) }

    var rotateX by remember { mutableStateOf(0f) }
    var rotateY by remember { mutableStateOf(0f) }

    var rotateYTo by remember { mutableStateOf(rotateY) }
    if (isRotating) {
        rotateY = animateFloatAsState(
            targetValue = rotateYTo,
            animationSpec = tween(durationMillis = 300),
            finishedListener = { isRotating = false }
        ).value
        LaunchedEffect(key1 = rotateY) {
            Log.d("JET_TAG", "rotateY = $rotateY")
        }
    }
    var composableCenterPosition: Offset? = remember { null }

    val onDoubleTap: (Offset) -> Unit = { offset ->
        composableCenterPosition?.let { centerPosition ->
            isFrontSide = !isFrontSide
            isRotating = true
            if (offset.x < centerPosition.x) {
                rotateYTo = (rotateY - 180f).roundUpToMultipleOf(180f)
                Log.d("JET_TAG", "LEFT: $rotateYTo")
            } else {
                rotateYTo = (rotateY + 180f).roundUpToMultipleOf(180f)
                Log.d("JET_TAG", "RIGHT: $rotateYTo")
            }
        }
    }
    val onDrag: (PointerInputChange, Offset) -> Unit = { _, dragAmount ->
        when {
            dragAmount.x > 0 && rotateY < 3f -> rotateY += 0.5f
            dragAmount.x < 0 && rotateY > -3f -> rotateY -= 0.5f
            dragAmount.y > 0 && rotateX > -6f -> rotateX -= 0.5f
            dragAmount.y < 0 && rotateX < 6f -> rotateX += 0.5f
        }
    }
    val onGloballyPositioned: (LayoutCoordinates) -> Unit = { coordinates ->
        composableCenterPosition = coordinates.boundsInParent().center
    }

//    val newRotateY = if (isRotatingLeft) animatedRotateLeft else if (isRotatingRight) animatedRotateRight else rotateY
    StatelessCardWrapper(
        modifier = modifier,
        rotateX = rotateX,
        rotateY = rotateY,
        onDrag = onDrag,
        onLongPress = {
            onLongPress?.invoke(isFrontSide)
        },
        onDoubleTap = onDoubleTap,
        onGloballyPositioned = onGloballyPositioned,
        cardContent = {
            cardContent.invoke(isFrontSide)
        }
    )
}
