package com.zhiroke.jetwallet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zhiroke.core.navigation.directions.MyWalletNavDirections
import com.zhiroke.jetwallet.navigation.subgraphs.myWalletGraph

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = MyWalletNavDirections.splash.destination,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier) {
        myWalletGraph()
    }
}