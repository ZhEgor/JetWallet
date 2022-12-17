package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.theme.demensions.dp_8
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.MagneticStripe
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.components.SignatureAndVerificationNumberRow


@Composable
internal fun StatelessBackSideCard(
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





