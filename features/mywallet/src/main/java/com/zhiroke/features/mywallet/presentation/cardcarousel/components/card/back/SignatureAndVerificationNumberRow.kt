package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
internal fun SignatureAndVerificationNumberRow(
    modifier: Modifier = Modifier,
    verificationNumber: String
) {

    Row(modifier = modifier) {

        AuthorizedSignatureStripe(modifier = modifier.weight(weight = 1f))

        VerificationNumberText(number = verificationNumber)
    }
}