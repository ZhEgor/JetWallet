package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.popups.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.utils.MaterialShapes
import com.zhiroke.core.cardrecognition.composable.CardRecognizer


@Composable
fun CardRecognizerPage(
    modifier: Modifier = Modifier,
    forceToLeaveComposition: Boolean,
    onReturnToPreviousPage: () -> Unit,
    onFrontSideCardRecognized: (cardNumber: String, expirationDate: String) -> Unit,
    onBackSideCardRecognized: (verificationNumber: String) -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        EuropeanCardRatioContainer(
            modifier = Modifier
                .clip(shape = MaterialShapes.medium)
                .background(color = Color.Black)
        ) {

            /* Current page doesn't leave composition, after changing paging to the previous one,
             * so we have to force to leave, to call onDispose.
             */
            if (!forceToLeaveComposition) {
                CardRecognizer(
                    modifier = Modifier.fillMaxSize(),
                    onDismissRequest = onReturnToPreviousPage,
                    onFrontSideCardRecognized = onFrontSideCardRecognized,
                    onBackSideCardRecognized = onBackSideCardRecognized
                )
            }
        }
    }
}