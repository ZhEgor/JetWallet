package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.components.animations.ShimmeringTextSize
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes


/**
 * For loading state
 */
@Composable
internal fun ShimmeringFrontSideCard() {

    key(Unit) {

        EuropeanCardRatioContainer(modifier = Modifier.clip(shape = MaterialShapes.medium)) {

            StatelessFrontSideCard(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialColor.primaryContainer)
                    .padding(all = dp_16),
                cardNumberContent = {

                    ShimmeringTextSize(text = "1234567812345678")
                },
                expirationDateContainer = {

                    ShimmeringTextSize(text = "11/99")
                },
                cardholderContainer = {

                    ShimmeringTextSize(text = "Egor Zhir")
                }
            )
        }
    }
}

@Preview
@Composable
private fun ShimmeringFrontSideCardPreview() {

    ShimmeringFrontSideCard()
}