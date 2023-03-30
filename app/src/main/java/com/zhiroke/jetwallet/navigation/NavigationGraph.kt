package com.zhiroke.jetwallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zhiroke.core.navigation.directions.AuthNavDirections
import com.zhiroke.jetwallet.navigation.subgraphs.authGraph
import com.zhiroke.jetwallet.navigation.subgraphs.myWalletGraph

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AuthNavDirections.auth.destination
) {
    NavHost(modifier = modifier, navController = navController, startDestination = startDestination) {
        authGraph()
        myWalletGraph()
    }
}