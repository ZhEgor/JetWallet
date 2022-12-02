package com.zhiroke.features.mywallet.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zhiroke.core.navigation.utils.getNavController


@Composable
fun SplashScreen() {
    val navController = getNavController()
    var isFrontSide by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            isFrontSide = !isFrontSide
//            navController.navigate(MyWalletNavDirections.cardCarousel.destination)
        }) {
            Text(text = "Navigate")
        }

    }
}

