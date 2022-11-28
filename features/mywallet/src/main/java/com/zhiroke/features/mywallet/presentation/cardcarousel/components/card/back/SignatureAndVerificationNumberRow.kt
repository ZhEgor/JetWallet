package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_4
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.common.CardTextContainer


@Composable
internal fun SignatureAndVerificationNumberRow(
    modifier: Modifier = Modifier,
    verificationNumber: String
) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        AuthorizedSignatureStripe(modifier = modifier.weight(weight = 1f))

        CardTextContainer {

            VerificationNumberText(
                modifier = Modifier.padding(vertical = dp_4, horizontal = dp_8),
                number = verificationNumber
            )
        }
    }
}