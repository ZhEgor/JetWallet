package com.zhiroke.jetwallet

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.zhiroke.core.navigation.utils.detachNavController
import com.zhiroke.jetwallet.ui.JetWallet
import com.zhiroke.presentation.auth.utils.managers.attachBiometricAuthManager
import com.zhiroke.presentation.auth.utils.managers.detachBiometricAuthManager

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!BuildConfig.DEBUG) {
            window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        }
        setContent(content = { JetWallet() })
        attachBiometricAuthManager(activity = this)
    }

    override fun onDestroy() {
        detachNavController()
        detachBiometricAuthManager()
        super.onDestroy()
    }
}