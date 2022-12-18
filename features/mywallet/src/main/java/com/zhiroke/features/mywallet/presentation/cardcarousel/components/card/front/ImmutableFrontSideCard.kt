package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes
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

        EuropeanCardRatioContainer(modifier = Modifier.clip(shape = MaterialShapes.medium)) {

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
                expirationDateContent = {

                    ExpirationDateText(
                        modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                        expirationDate = bankCard.expirationDate
                    )
                },
                cardholderContent = {

                    CardholderText(
                        modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                        cardholder = bankCard.cardholderName
                    )
                },
                skinContent = {

                    AsyncImage(
                        modifier = Modifier.fillMaxSize().clip(MaterialShapes.medium),
                        model = bankCard.skinUri,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
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
        bankCard = BankCard.dummy()
    )
}