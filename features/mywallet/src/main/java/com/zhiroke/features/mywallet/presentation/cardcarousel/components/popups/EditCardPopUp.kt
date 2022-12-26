package com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.zhiroke.core.components.popup.PopUp
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.EditBankCardCallbacks
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.EditableCardPage
import com.zhiroke.features.mywallet.utils.isValid


@Composable
internal fun EditCardPopUp(popUpState: PopUpState, bankCard: BankCard, onHide: () -> Unit, onSaveClick: (BankCard) -> Unit) {

    var bankCardState by remember(bankCard.number) { mutableStateOf(bankCard) }

    PopUp(popUpState = popUpState, onHide = onHide) {

        EditableCardPage(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = dp_16, start = dp_16, end = dp_16),
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
                onPhotoUriChange = { value ->
                    bankCardState = bankCardState.copy(skinUri = value)
                }
            ),
            onDone = {
                if (!bankCardState.isValid()) return@EditableCardPage
                onHide.invoke()
                onSaveClick.invoke(bankCardState)
            }
        )
    }
}