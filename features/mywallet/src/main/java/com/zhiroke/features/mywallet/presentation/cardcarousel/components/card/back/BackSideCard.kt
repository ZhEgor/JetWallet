package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.theme.demensions.dp_12
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.domain.models.BankCard


/**
 * Aspect ratio of a European bank card
 */
@Composable
internal fun BackSideCard(bankCard: BankCard) {

    key(bankCard.number) {

        StatelessBackSideCard(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(dp_12))
                .fillMaxWidth()
                .aspectRatio(1.647f)
                .background(color = MaterialColor.primaryContainer)
                .graphicsLayer(rotationY = 180f),
            bankCard = bankCard
        )
    }
}

@Composable
private fun StatelessBackSideCard(modifier: Modifier = Modifier, bankCard: BankCard) {

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
                verificationNumber = bankCard.verificationNumber
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackSideCardPreview() {
    BackSideCard(
        bankCard = BankCard(
            number = "1111222233334444",
            cardholderName = "Yehor Zhyr",
            expirationDate = "12/12",
            verificationNumber = "546"
        )
    )
}