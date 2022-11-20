package com.zhiroke.features.mywallet.presentation.splash

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.zhiroke.core.navigation.utils.getNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun SplashScreen() {
    val navController = getNavController()
    var isFrontSide by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            isFrontSide = !isFrontSide
//            navController.navigate(MyWalletNavDirections.cardCarousel.destination)
        }) {
            Text(text = "Navigate")
        }

        StatefulInteractiveCard()

    }
}

infix fun Float.roundUpToMultipleOf(multiplier: Float): Float {
    val diff = this % multiplier
    return if (multiplier / 2 > diff) {
        this - diff
    } else {
        this + multiplier - diff
    }
}

fun Float.reverseSignIf(predicate: Boolean): Float = if (predicate) this * -1 else this

@Composable
fun StatefulInteractiveCard() {
//    val onClick = rememberLambda {
//        isFrontSide = !isFrontSide
//    }
    var isFrontSide by remember { mutableStateOf(true) }
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

    LaunchedEffect(Unit) {
        isFrontSide = !isFrontSide
        isRotating = true
        (0..10).forEach {
            delay(1000)
            withContext(Dispatchers.Main) {
                rotateYTo = (rotateY - 180f).roundUpToMultipleOf(180f)
            }
        }
    }
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
    val onLongPress: (Offset) -> Unit  = { offset ->
        Log.d("JET_TAG", "Copy!!!")
    }
    val onDrag: (PointerInputChange, Offset) -> Unit = { change, dragAmount ->
//        change.consume()
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
    StatelessInteractiveCard(
        rotateX = rotateX,
        rotateY = rotateY,
        onDrag = onDrag,
        onDoubleTap = onDoubleTap,
        onLongPress = onLongPress,
        onGloballyPositioned = onGloballyPositioned
    )
}

@Composable
fun StatelessInteractiveCard(
    rotateX: Float,
    rotateY: Float,
    onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit,
    onDoubleTap: (Offset) -> Unit,
    onLongPress: (Offset) -> Unit,
    onGloballyPositioned: (LayoutCoordinates) -> Unit
) {
    Box(modifier = Modifier.size(300.dp, 150.dp)) {
        Box(modifier = Modifier
            .onGloballyPositioned(onGloballyPositioned = onGloballyPositioned)
            .pointerInput(Unit) {
                detectTapGestures(onDoubleTap = onDoubleTap, onLongPress = onLongPress)
            }
            .pointerInput(Unit) {
                detectDragGestures(onDrag = onDrag)
            }
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, 0.5f)
                rotationX = rotateX
                rotationY = rotateY
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.Magenta)
            )
        }
    }
}
