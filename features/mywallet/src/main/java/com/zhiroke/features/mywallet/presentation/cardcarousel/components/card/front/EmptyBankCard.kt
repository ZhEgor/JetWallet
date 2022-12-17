package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.zhiroke.core.components.card.EuropeanCardRatioContainer
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_48
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.core.theme.utils.MaterialShapes
import com.zhiroke.features.mywallet.R
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun EmptyBankCard(modifier: Modifier = Modifier) {

    EuropeanCardRatioContainer(modifier = modifier) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = MaterialShapes.medium)
                .background(color = MaterialColor.primaryContainer),
            contentAlignment = Alignment.Center
        ) {

            CardTextContainer(
                modifier = Modifier
                    .clip(shape = MaterialShapes.medium)
                    .padding(horizontal = dp_48)
            ) {

                Text(
                    modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                    text = stringResource(R.string.empty_card_text),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}