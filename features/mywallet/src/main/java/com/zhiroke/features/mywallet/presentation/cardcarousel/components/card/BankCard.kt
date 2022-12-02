package com.zhiroke.features.mywallet.presentation.cardcarousel.components.card

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.common.utils.rememberLambda
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_32
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.BackSideCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.FrontSideCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.cardwrapper.RotatingCardWrapper


@Composable
fun BankCard(card: BankCard, onCopy: (String) -> Unit) {

    RotatingCardWrapper(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = dp_16, vertical = dp_32),
        onLongPress = rememberLambda { isFrontSide ->
            if (isFrontSide) {
                onCopy.invoke(card.number)
                Log.d("JET_TAG", "COPY")
            }
        }
    ) { isFrontSide ->
        if (isFrontSide) {
            FrontSideCard(bankCard = card)
        } else {
            BackSideCard(bankCard = card)
        }
    }
}