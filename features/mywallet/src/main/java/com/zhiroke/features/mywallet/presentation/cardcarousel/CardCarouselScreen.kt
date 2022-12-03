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
import com.zhiroke.features.mywallet.presentation.cardcarousel.button.AddCardButton
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.BankCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.popups.CreateCardPopUp
import org.koin.androidx.compose.getViewModel


@Composable
fun CardCarouselRoute() {

    CardCarouselScreen(viewModel = getViewModel())
}

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalPagerApi::class)
@Composable
private fun CardCarouselScreen(viewModel: CardCarouselViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {

        if (!state.areCardsLoading) {

            HorizontalPagerWithTransition(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                count = state.cards.size
            ) { page ->

                BankCard(card = state.cards[page], onCopy = context::copyToClipboardWithVibration)
            }
        }

        AddCardButton(onClick = viewModel::showPopUpCreate)

        CreateCardPopUp(popUpState = state.createCardPopUp, onHide = viewModel::hidePopUpCreate)
    }
}

@Preview
@Composable
private fun CardCarouselScreenPreview() {

    CardCarouselScreen(viewModel = getDummyCardCarouselViewModel())
}