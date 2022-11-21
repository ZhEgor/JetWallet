package com.zhiroke.features.mywallet.presentation.cardcarousel

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhiroke.core.common.utils.copyToClipboardWithVibration
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.BankCard
import org.koin.androidx.compose.getViewModel


@Composable
fun CardCarouselRoute() {
    CardCarouselScreen(viewModel = getViewModel())
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun CardCarouselScreen(viewModel: CardCarouselViewModel) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Box {

        if (!state.areCardsLoading) {
            BankCard(card = state.cards.first(), onCopy = context::copyToClipboardWithVibration)
        }
    }
}

@Preview
@Composable
private fun CardCarouselScreenPreview() {
    CardCarouselScreen(viewModel = getDummyCardCarouselViewModel())
}