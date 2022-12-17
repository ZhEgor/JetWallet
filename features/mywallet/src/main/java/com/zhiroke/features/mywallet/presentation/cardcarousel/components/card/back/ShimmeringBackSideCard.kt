package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.components.animations.ShimmeringTextSize
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.VerificationNumberContainer


/**
 * For loading state
 */
@Composable
internal fun ShimmeringBackSideCard() {

    key(Unit) { // ToDo: check for necessaries

        EuropeanCardRatioContainer(modifier = Modifier.clip(shape = MaterialShapes.medium)) {

            StatelessBackSideCard(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialColor.primaryContainer)
                    .graphicsLayer(rotationY = 180f),
                verificationNumberContent = {

                    VerificationNumberContainer {

                        ShimmeringTextSize(text = "123")
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun ShimmeringBackSideCardPreview() {

    ShimmeringBackSideCard()
}