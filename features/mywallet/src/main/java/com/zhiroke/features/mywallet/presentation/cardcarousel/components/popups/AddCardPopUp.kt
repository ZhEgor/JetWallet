package com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zhiroke.core.components.popup.PopUp
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.domain.models.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.CardRecognizerPage
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.EditBankCardCallbacks
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.components.EditableCardPage
import com.zhiroke.features.mywallet.utils.isValid
import kotlinx.coroutines.launch


/**
 * PS: Sequence of displaying a page will be the same
 */
private enum class CardEditMode {
    Manual, Scanner
}

@OptIn(ExperimentalPagerApi::class)
@Composable
internal fun AddCardPopUp(popUpState: PopUpState, onHide: () -> Unit, onAddClick: (BankCard) -> Unit) {

    var bankCardState by remember { mutableStateOf(BankCard.empty()) }
    val pagerState = rememberPagerState(initialPage = CardEditMode.Manual.ordinal)
    val scope = rememberCoroutineScope()

    PopUp(popUpState = popUpState, onHide = onHide) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = dp_16),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            HorizontalPager(
                modifier = Modifier.weight(weight = 1f),
                state = pagerState,
                count = CardEditMode.values().size,
                itemSpacing = dp_16,
                contentPadding = PaddingValues(horizontal = dp_16),
            ) { page ->

                when(page) {

                    CardEditMode.Manual.ordinal -> EditableCardPage(
                        modifier = Modifier.fillMaxSize(),
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
                        onDone = {
                            if (!bankCardState.isValid()) return@EditableCardPage
                            onHide.invoke()
                            onAddClick.invoke(bankCardState)
                            bankCardState = BankCard.empty()
                        }
                    )

                    CardEditMode.Scanner.ordinal -> CardRecognizerPage(
                        modifier = Modifier.fillMaxSize(),
                        forceToLeaveComposition = !(pagerState.currentPage == CardEditMode.Scanner.ordinal && popUpState.isShown),
                        onReturnToPreviousPage = {
                            scope.launch {
                                pagerState.animateScrollToPage(page = CardEditMode.Manual.ordinal)
                            }
                        },
                        onCardRecognized = { cardNumber, expirationDate ->
                            bankCardState = bankCardState.copy(number = cardNumber, expirationDate = expirationDate)
                            scope.launch {
                                pagerState.animateScrollToPage(page = CardEditMode.Manual.ordinal)
                            }
                        },
                    )
                }
            }
        }
    }
}
