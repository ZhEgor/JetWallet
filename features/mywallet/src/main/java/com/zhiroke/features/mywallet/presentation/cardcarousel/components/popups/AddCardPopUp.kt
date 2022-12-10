package com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.zhiroke.core.common.utils.rememberLambda
import com.zhiroke.core.components.buttons.ButtonText
import com.zhiroke.core.components.popup.PopUp
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.R
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.EditBankCardCallbacks
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.EditableCardContainer
import com.zhiroke.features.mywallet.utils.isValid


@Composable
internal fun AddCardPopUp(popUpState: PopUpState, onHide: () -> Unit, onAddClick: (BankCard) -> Unit) {

    var bankCardState by remember { mutableStateOf(BankCard.empty()) }
    val onDone = rememberLambda {
        onHide.invoke()
        onAddClick.invoke(bankCardState)
        bankCardState = BankCard.empty()
    }

    PopUp(popUpState = popUpState, onHide = onHide) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = dp_16),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            EditableCardContainer(
                modifier = Modifier.weight(1f),
                bankCard = bankCardState,
                editBankCardCallbacks = EditBankCardCallbacks(
                    onNumberChange = { value ->
                        bankCardState = bankCardState.copy(number = value)
                    },
                    onExpirationDateChange = { value ->
                        bankCardState = bankCardState.copy(expirationDate = value)
                    },
                    onCardholderChange = { value ->
                        bankCardState = bankCardState.copy(cardholderName = value)
                    },
                    onVerificationNumberChange = { value ->
                        bankCardState = bankCardState.copy(verificationNumber = value)
                    },
                ),
                onDone = onDone
            )

            ButtonText(
                modifier = Modifier.fillMaxWidth(),
                textResId = R.string.add_card_popup_button_add,
                onClick = onDone,
                enabled = bankCardState.isValid()
            )
        }
    }
}
