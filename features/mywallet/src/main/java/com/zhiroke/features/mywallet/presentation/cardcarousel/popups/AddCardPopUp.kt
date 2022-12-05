package com.zhiroke.features.mywallet.presentation.cardcarousel.popups

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.zhiroke.core.components.buttons.ButtonText
import com.zhiroke.core.components.popup.PopUp
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.theme.demensions.dp_16
import java.util.UUID

internal data class RawBankCard(
    val number: String,
    val cardholderName: String,
    val expirationDate: String,
    val verificationNumber: String,
) {

    companion object {

        fun initialState() = RawBankCard(
            number = "",
            cardholderName = "",
            expirationDate = "",
            verificationNumber = "",
        )
    }
}

@Composable
internal fun AddCardPopUp(popUpState: PopUpState, onHide: () -> Unit, onAddClick: (RawBankCard) -> Unit) {

    var rawBankCard: RawBankCard = remember { RawBankCard.initialState() }

    PopUp(popUpState = popUpState, onHide = onHide) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

            ButtonText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = dp_16), text = "Add"
            ) {
                rawBankCard = rawBankCard.copy(
                    number = UUID.randomUUID().toString().take(8),
                    cardholderName = UUID.randomUUID().toString().take(4) + " " + UUID.randomUUID().toString().take(4),
                    expirationDate = "11/29",
                    verificationNumber = "456"
                )
                onAddClick.invoke(rawBankCard)
                rawBankCard = RawBankCard.initialState()
                onHide.invoke()
            }
        }
    }
}
