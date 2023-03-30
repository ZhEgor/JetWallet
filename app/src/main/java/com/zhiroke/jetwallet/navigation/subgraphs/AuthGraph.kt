package com.zhiroke.jetwallet.navigation.subgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zhiroke.core.navigation.directions.AuthNavDirections
import com.zhiroke.features.auth.presentation.auth.AuthRoute

fun NavGraphBuilder.authGraph() {
    composable(route = AuthNavDirections.auth.destination) {
        AuthRoute()
    }
}