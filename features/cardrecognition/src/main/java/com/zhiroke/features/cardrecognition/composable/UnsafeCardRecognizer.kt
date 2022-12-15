package com.zhiroke.features.cardrecognition.composable

import android.util.Log
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.zhiroke.features.cardrecognition.recognizer.DigitRecognizer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun UnsafeCardRecognizer(
    modifier: Modifier = Modifier,
    onCardRecognized: (cardNumber: String, expirationDate: String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraController = remember { LifecycleCameraController(context) }
    val cameraView = remember { PreviewView(context) }

    val cameraExecutor = remember { Dispatchers.Default.limitedParallelism(2).asExecutor() }

    val textScanner = remember { TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS) }


    LaunchedEffect(Unit) {
        var recognizedCardNumber: String? = null
        var recognizedExpirationDate: String? = null
        Log.d("JET_TAG", "onCreate")

        val cardNumberRecognizer = DigitRecognizer(pattern = "xxxx xxxx xxxx xxxx") { cardNumber ->
            recognizedCardNumber = cardNumber
            recognizedExpirationDate?.let { expirationDate ->
                onCardRecognized.invoke(cardNumber, expirationDate)
            }
            Log.d("JET_TAG", "\n\n\n\n\nbest match: $cardNumber")
        }
        val expirationDateRecognizer = DigitRecognizer(pattern = "xx/xx") { expirationDate ->
            recognizedExpirationDate = expirationDate
            recognizedCardNumber?.let { cardNumber ->
                onCardRecognized.invoke(cardNumber, expirationDate)
            }
            Log.d("JET_TAG", "\n\n\n\n\nbest match: $recognizedExpirationDate")
        }

        cameraController.setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
        cameraController.bindToLifecycle(lifecycleOwner)
        cameraController.setImageAnalysisAnalyzer(
            cameraExecutor,
            MlKitAnalyzer(
                listOf(textScanner),
                CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED,
                cameraExecutor
            ) { result ->
                val text = result.getValue(textScanner)
                text?.textBlocks?.forEach { textBlock ->
                    cardNumberRecognizer.feed(textBlock.text)
                    expirationDateRecognizer.feed(textBlock.text)
                }
            }
        )

        cameraView.controller = cameraController
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.d("JET_TAG", "onDispose")
            cameraView.controller = null
            cameraController.unbind()
        }
    }

    Box(modifier = modifier.background(Color.Black)) {

        AndroidView(factory = { cameraView }, modifier = Modifier.fillMaxSize())
    }
}