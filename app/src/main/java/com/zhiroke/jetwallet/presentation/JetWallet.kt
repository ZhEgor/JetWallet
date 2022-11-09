package com.zhiroke.jetwallet.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zhiroke.jetwallet.navigation.NavigationGraph
import com.zhiroke.jetwallet.ui.theme.JetWalletTheme

@Composable
internal fun JetWallet() {
    val navController = rememberNavController()

    JetWalletTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavigationGraph(navController = navController)
        }
    }
}