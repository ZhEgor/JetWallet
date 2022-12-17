package com.zhiroke.features.mywallet.presentation.cardcarousel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.pager.ExperimentalPagerApi
import com.zhiroke.core.common.utils.copyToClipboardWithVibration
import com.zhiroke.core.components.pager.HorizontalPagerWithTransition
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.button.AddCardButton
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.ShimmeringBankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.AddCardPopUp
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.popups.EditCardPopUp
import org.koin.androidx.compose.getViewModel


@Composable
fun CardCarouselRoute() {

    CardCarouselScreen(viewModel = getViewModel())
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun CardCarouselScreen(viewModel: CardCarouselViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(modifier = Modifier.fillMaxSize()) {

        when(state.cardsState) {
            is CardsState.Loaded -> CardsAreLoaded(viewModel, state = state.cardsState as CardsState.Loaded)
            CardsState.Loading -> CardsAreLoading()
            CardsState.Empty -> CardsAreEmpty()
        }

        AddCardButton(onClick = viewModel::showAddPopUp)

        AddCardPopUp(
            popUpState = state.addCardPopUp,
            onHide = viewModel::hideAddPopUp,
            onAddClick = viewModel::addBankCard
        )

        state.cardToEdit?.let { bankCard ->
            EditCardPopUp(
                popUpState = state.editCardPopUp,
                bankCard = bankCard,
                onHide = viewModel::hideEditPopUp,
                onSaveClick = viewModel::saveBankCard
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CardsAreLoaded(
    viewModel: CardCarouselViewModel,
    state: CardsState.Loaded
) {

    val context = LocalContext.current

    HorizontalPagerWithTransition(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        count = state.cards.size
    ) { page ->

        BankCard(
            card = state.cards[page],
            onCopy = context::copyToClipboardWithVibration,
            onEditClick = viewModel::showEditPopUp,
            onDeleteClick = viewModel::deleteBankCard
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun CardsAreLoading() {

    HorizontalPagerWithTransition(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        count = 1
    ) {

        ShimmeringBankCard()
    }
}

@Composable
private fun CardsAreEmpty() {

}

@Preview
@Composable
private fun CardCarouselScreenPreview() {

    CardCarouselScreen(viewModel = getDummyCardCarouselViewModel())
}