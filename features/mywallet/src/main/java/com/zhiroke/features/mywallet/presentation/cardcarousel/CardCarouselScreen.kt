package com.zhiroke.features.mywallet.presentation.cardcarousel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zhiroke.core.common.utils.copyToClipboardWithVibration
import com.zhiroke.core.theme.demensions.dp_16
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.back.BackSideCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.card.front.FrontSideCard
import com.zhiroke.features.mywallet.presentation.cardcarousel.components.cardwrapper.RotatingCardWrapper
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
            RotatingCardWrapper(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(all = dp_16)
            ) { isFrontSide ->
                if (isFrontSide) {
                    FrontSideCard(
                        bankCard = state.cards.first(),
                        onCopyNumber = context::copyToClipboardWithVibration
                    )
                } else {
                    BackSideCard(bankCard = state.cards.first())
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardCarouselScreenPreview() {
    CardCarouselScreen(viewModel = getDummyCardCarouselViewModel())
}