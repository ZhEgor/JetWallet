package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.theme.demensions.dp_12
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.domain.models.BankCard


/**
 * Aspect ratio of a European bank card
 */
@Composable
internal fun FrontSideCard(bankCard: BankCard) {

    key(bankCard.number) {

        StatelessFrontSideCard(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(dp_12))
                .fillMaxWidth()
                .aspectRatio(1.647f)
                .background(color = MaterialColor.primaryContainer)
                .padding(all = dp_16),
            bankCard = bankCard
        )
    }
}

@Composable
private fun StatelessFrontSideCard(modifier: Modifier = Modifier, bankCard: BankCard) {

    Box(modifier = modifier) {

        CardNumberContainer(
            modifier = Modifier.align(alignment = Alignment.Center),
            number = bankCard.number
        )

        ExpirationDateContainer(
            modifier = Modifier.align(alignment = Alignment.BottomStart),
            expirationDate = bankCard.expirationDate
        )

        CardholderContainer(
            modifier = Modifier.align(alignment = Alignment.BottomEnd),
            cardholder = bankCard.cardholderName
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FrontSideCardPreview() {
    FrontSideCard(
        bankCard = BankCard(
            number = "1111222233334444",
            cardholderName = "Yehor Zhyr",
            expirationDate = "12/12",
            verificationNumber = "546"
        )
    )
}