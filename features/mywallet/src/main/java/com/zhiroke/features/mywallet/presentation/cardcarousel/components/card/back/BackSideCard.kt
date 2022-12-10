package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.demensions.dp_12
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.domain.models.BankCard


@Composable
private fun StatelessBackSideCard(
    modifier: Modifier = Modifier,
    verificationNumberContent: @Composable () -> Unit
) {

    Box(modifier = modifier) {

        Column(
            modifier = Modifier.fillMaxHeight(fraction = 0.6f),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            MagneticStripe()

            SignatureAndVerificationNumberRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = dp_8),
                verificationNumberContent = verificationNumberContent
            )
        }
    }
}


/**
 * Immutable - because we use Text() for content
 */
@Composable
internal fun ImmutableBackSideCard(bankCard: BankCard) {

    key(bankCard.number) {

        EuropeanCardRatioContainer {

            StatelessBackSideCard(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(dp_12))
                    .fillMaxSize()
                    .background(color = MaterialColor.primaryContainer)
                    .graphicsLayer(rotationY = 180f),
                verificationNumberContent = {

                    VerificationNumberText(
                        modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                        number = bankCard.verificationNumber
                    )
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BackSideCardPreview() {
    ImmutableBackSideCard(
        bankCard = BankCard(
            id = "1",
            number = "1111222233334444",
            cardholderName = "Yehor Zhyr",
            expirationDate = "12/12",
            verificationNumber = "546"
        )
    )
}