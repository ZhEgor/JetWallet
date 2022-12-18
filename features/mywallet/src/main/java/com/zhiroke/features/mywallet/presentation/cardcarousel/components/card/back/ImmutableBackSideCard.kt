package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.VerificationNumberText


/**
 * Immutable - because we use Text() for content
 */
@Composable
internal fun ImmutableBackSideCard(bankCard: BankCard) {

    key(bankCard.number) { // ToDo: check for necessaries

        EuropeanCardRatioContainer(modifier = Modifier.clip(shape = MaterialShapes.medium)) {

            StatelessBackSideCard(
                modifier = Modifier
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
private fun ImmutableBackSideCardPreview() {

    ImmutableBackSideCard(
        bankCard = BankCard.dummy()
    )
}