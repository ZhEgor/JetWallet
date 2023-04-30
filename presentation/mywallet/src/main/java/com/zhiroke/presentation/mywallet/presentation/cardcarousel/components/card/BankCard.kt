package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zhiroke.core.common.utils.rememberLambda
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.core.theme.demensions.dp_24
import com.zhiroke.core.theme.demensions.dp_32
import com.zhiroke.core.theme.utils.MaterialColor
import com.zhiroke.domain.models.BankCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.back.ImmutableBackSideCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.front.ImmutableFrontSideCard
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.cardwrapper.RotatingCardWrapper


@Composable
internal fun BankCard(
    card: BankCard, onCopy: (String) -> Unit,
    onEditClick: (BankCard) -> Unit,
    onDeleteClick: (BankCard) -> Unit
) {

    RotatingCardWrapper(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = dp_16, vertical = dp_32),
        onLongPress = rememberLambda { isFrontSide ->
            if (isFrontSide) {
                onCopy.invoke(card.number)
            }
        }
    ) { isFrontSide ->

        if (isFrontSide) {
            ImmutableFrontSideCard(
                bankCard = card,
                toolsContent = {

                    IconButton(onClick = { onEditClick.invoke(card) }) {
                       Icon(
                           modifier = Modifier.size(dp_24),
                           imageVector = Icons.Rounded.Edit,
                           contentDescription = null,
                           tint = MaterialColor.primary
                       )
                    }

                    IconButton(onClick = { onDeleteClick.invoke(card) }) {
                       Icon(
                           modifier = Modifier.size(dp_24),
                           imageVector = Icons.Rounded.Delete,
                           contentDescription = null,
                           tint = MaterialColor.primary
                       )
                    }
                }
            )
        } else {
            ImmutableBackSideCard(bankCard = card)
        }
    }
}
