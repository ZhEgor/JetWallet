package com.zhiroke.jetwallet

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.zhiroke.core.navigation.utils.detachNavController
import com.zhiroke.features.auth.utils.managers.attachBiometricAuthManager
import com.zhiroke.features.auth.utils.managers.detachBiometricAuthManager
import com.zhiroke.jetwallet.ui.JetWallet

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent(content = { JetWallet() })
        attachBiometricAuthManager(activity = this)
    }

    override fun onDestroy() {
        detachNavController()
        detachBiometricAuthManager()
        super.onDestroy()
    }
}