package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.demensions.dp_12
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardNumberText
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.CardholderText
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.ExpirationDateText
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.components.ToolsContainer


/**
 * Immutable - because we use Text() for content
 */
@Composable
internal fun ImmutableFrontSideCard(
    bankCard: BankCard,
    toolsContent: @Composable (RowScope.() -> Unit)? = null
) {

    key(bankCard.number) {

        EuropeanCardRatioContainer(modifier = Modifier.clip(shape = RoundedCornerShape(dp_12))) {

            StatelessFrontSideCard(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialColor.primaryContainer)
                    .padding(all = dp_16),
                cardNumberContent = {

                    CardNumberText(
                        modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                        number = bankCard.number
                    )
                },
                expirationDateContainer = {

                    ExpirationDateText(
                        modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                        expirationDate = bankCard.expirationDate
                    )
                },
                cardholderContainer = {

                    CardholderText(
                        modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                        cardholder = bankCard.cardholderName
                    )
                }
            )

            ToolsContainer(toolsContent = toolsContent)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun FrontSideCardPreview() {
    ImmutableFrontSideCard(
        bankCard = BankCard(
            id = "1",
            number = "1111222233334444",
            cardholderName = "Yehor Zhyr",
            expirationDate = "12/12",
            verificationNumber = "546"
        )
    )
}