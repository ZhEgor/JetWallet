package com.zhiroke.features.auth.utils.managers

import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.zhiroke.core.common.utils.vibrate
import com.zhiroke.features.auth.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext
import kotlin.coroutines.suspendCoroutine

class BiometricAuthManager {

    private var activity: FragmentActivity? = null

    fun attach(activity: FragmentActivity) {
        this.activity = activity
    }

    fun detach() {
        activity = null
    }

    suspend fun auth(): Int? = suspendCoroutine { continuation ->
        activity?.let { activity ->
            val promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(activity.getString(R.string.biometric_title))
                .setNegativeButtonText(activity.getString(R.string.biometric_negative_btn))
                .setAllowedAuthenticators(BIOMETRIC_STRONG)
                .build()

            val executor = ContextCompat.getMainExecutor(activity)

            val biometricPrompt = BiometricPrompt(
                activity,
                executor,
                object : BiometricPrompt.AuthenticationCallback() {

                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                        continuation.resumeWith(Result.success(errorCode))
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        continuation.resumeWith(Result.success(null))
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        activity.vibrate()
                    }
                }
            )
            CoroutineScope(Dispatchers.Main).launch {
                biometricPrompt.authenticate(promptInfo)
            }
        }
    }

    fun isBioAuthSupported(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) return false
        return activity?.let { activity ->
            when (BiometricManager.from(activity).canAuthenticate(BIOMETRIC_STRONG)) {
                BiometricManager.BIOMETRIC_SUCCESS -> true
                else -> false
            }
        } ?: false
    }
}

fun attachBiometricAuthManager(activity: FragmentActivity) {
    getBiometricAuthManager().attach(activity = activity)
}

fun detachBiometricAuthManager() {
    getBiometricAuthManager().detach()
}

private fun getBiometricAuthManager(): BiometricAuthManager {
    return GlobalContext.get().get<BiometricAuthManager>()
}
