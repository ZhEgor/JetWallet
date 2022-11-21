package com.zhiroke.core.components.cardwrapper

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import com.zhiroke.core.components.utils.ifThen


@Composable
fun StatelessCardWrapper(
    modifier: Modifier = Modifier,
    rotateX: Float,
    rotateY: Float,
    onDrag: ((change: PointerInputChange, dragAmount: Offset) -> Unit)? = null,
    onDoubleTap: ((Offset) -> Unit)? = null,
    onLongPress: ((Offset) -> Unit)? = null,
    onGloballyPositioned: ((LayoutCoordinates) -> Unit)? = null,
    cardContent: @Composable () -> Unit
) {

    Box(modifier = modifier) {

        Box(
            modifier = Modifier
                .ifThen(predicate = onGloballyPositioned != null) {
                    onGloballyPositioned(onGloballyPositioned = onGloballyPositioned!!) // Android Studio bug
                }
                .pointerInput(Unit) {
                    detectTapGestures(onDoubleTap = onDoubleTap, onLongPress = onLongPress)
                }
                .ifThen(predicate = onDrag != null) {
                    pointerInput(Unit) {
                        detectDragGestures(onDrag = onDrag!!)
                    }
                }
                .graphicsLayer {
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                    rotationX = rotateX
                    rotationY = rotateY
                }
        ) {

            cardContent.invoke()
        }
    }
}
