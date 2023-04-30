package com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.popups.components

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.zhiroke.core.components.buttons.ButtonText
import com.zhiroke.core.components.column.ScrollableColumn
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.domain.models.BankCard
import com.zhiroke.presentation.mywallet.R
import com.zhiroke.presentation.mywallet.presentation.cardcarousel.components.card.EditableBankCard
import com.zhiroke.presentation.mywallet.utils.*


internal class EditBankCardCallbacks(
    val onNumberChange: (String) -> Unit,
    val onExpirationDateChange: (String) -> Unit,
    val onCardholderChange: (String) -> Unit,
    val onVerificationNumberChange: (String) -> Unit,
    val onPhotoUriChange: (Uri?) -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun EditableCardPage(
    modifier: Modifier = Modifier,
    bankCard: BankCard,
    editBankCardCallbacks: EditBankCardCallbacks,
    onDone: () -> Unit,
) {

    var isFrontSide by remember { mutableStateOf(true) }
    val localFocusManager = LocalFocusManager.current

    ScrollableColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            EditableBankCard(bankCard = bankCard, isFrontSide = isFrontSide, onPhotoUriChange = editBankCardCallbacks.onPhotoUriChange)

            Spacer(modifier = Modifier.height(dp_16))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) isFrontSide = true
                    },
                value = bankCard.number,
                onValueChange = editBankCardCallbacks.onNumberChange,
                label = {
                    Text(text = stringResource(id = R.string.card_number_label))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    localFocusManager.moveFocus(FocusDirection.Down)
                }),
                isError = !bankCard.number.isNumberValid()
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) isFrontSide = true
                    },
                value = bankCard.expirationDate,
                onValueChange = editBankCardCallbacks.onExpirationDateChange,
                label = {
                    Text(text = stringResource(id = R.string.expiration_date_label))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    localFocusManager.moveFocus(FocusDirection.Down)
                }),
                isError = !bankCard.expirationDate.isExpirationDateValid()
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) isFrontSide = true
                    },
                value = bankCard.cardholderName,
                onValueChange = editBankCardCallbacks.onCardholderChange,
                label = {
                    Text(text = stringResource(id = R.string.cardholder_name_label))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    localFocusManager.moveFocus(FocusDirection.Down)
                }),
                isError = !bankCard.cardholderName.isCardholderNameValid()
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged {
                        if (it.isFocused) isFrontSide = false
                    },
                value = bankCard.verificationNumber,
                onValueChange = editBankCardCallbacks.onVerificationNumberChange,
                label = {
                    Text(text = stringResource(id = R.string.verification_number_label))
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = { onDone.invoke() }),
                isError = !bankCard.verificationNumber.isVerificationNumberValid()
            )

            Spacer(modifier = Modifier.height(dp_16))
        }

        ButtonText(
            modifier = Modifier.fillMaxWidth(),
            textResId = R.string.edit_card_popup_button_save,
            onClick = onDone,
            enabled = bankCard.isValid()
        )
    }
}
