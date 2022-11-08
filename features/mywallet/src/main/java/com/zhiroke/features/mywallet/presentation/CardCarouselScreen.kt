package com.zhiroke.features.mywallet.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.getViewModel

@Composable
fun CardCarouselScreen() {
    CardCarouselScreenImpl(viewModel = getViewModel())
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun CardCarouselScreenImpl(viewModel: CardCarouselViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Text(text = "Hello World!")

    LazyColumn {
        items(items = state.cards, key = { it.number }) { card ->

            Text(text = card.number)

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
private fun CardCarouselScreenPreview() {
    CardCarouselScreenImpl(viewModel = getDummyCardCarouselViewModel())
}