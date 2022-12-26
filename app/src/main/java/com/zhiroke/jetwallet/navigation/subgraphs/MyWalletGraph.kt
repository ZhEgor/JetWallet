package com.zhiroke.jetwallet.navigation.subgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhiroke.core.navigation.directions.MyWalletNavDirections
import com.zhiroke.features.mywallet.presentation.cardcarousel.CardCarouselRoute

fun NavGraphBuilder.myWalletGraph() {
    composable(route = MyWalletNavDirections.cardCarousel.destination) {
        CardCarouselRoute()
    }
}