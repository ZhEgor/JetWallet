package com.zhiroke.jetwallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zhiroke.core.navigation.utils.detachNavController
import com.zhiroke.jetwallet.ui.JetWallet

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = { JetWallet() })
    }

    override fun onDestroy() {
        detachNavController()
        super.onDestroy()
    }
}