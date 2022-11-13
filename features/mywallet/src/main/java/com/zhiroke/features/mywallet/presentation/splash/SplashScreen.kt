package com.zhiroke.features.mywallet.presentation.splash

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.zhiroke.core.navigation.directions.MyWalletNavDirections
import com.zhiroke.core.navigation.utils.getNavController


@Composable
fun SplashScreen() {
    Text(text = "Hello, World!")
    val navController = getNavController()

    Button(onClick = {
        navController.navigate(MyWalletNavDirections.cardCarousel.destination)
    }) {
        Text(text = "Navigate")
    }
}