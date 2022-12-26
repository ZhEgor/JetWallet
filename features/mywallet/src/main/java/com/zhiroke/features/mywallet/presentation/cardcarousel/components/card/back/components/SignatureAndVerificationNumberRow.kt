package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
internal fun SignatureAndVerificationNumberRow(
    modifier: Modifier = Modifier,
    verificationNumberContent: @Composable () -> Unit
) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        AuthorizedSignatureStripe(modifier = modifier.weight(weight = 1f))

        VerificationNumberContainer(content = verificationNumberContent)
    }
}