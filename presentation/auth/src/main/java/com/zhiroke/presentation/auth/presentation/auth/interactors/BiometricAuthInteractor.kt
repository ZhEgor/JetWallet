package com.zhiroke.presentation.auth.presentation.auth.interactors

import android.content.Context
import androidx.biometric.BiometricPrompt
import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.presentation.auth.R
import com.zhiroke.presentation.auth.presentation.auth.AuthEvent
import com.zhiroke.presentation.auth.presentation.auth.AuthState
import com.zhiroke.presentation.auth.utils.managers.BiometricAuthManager

internal class BiometricAuthInteractor(
    private val biometricAuthManager: BiometricAuthManager,
    private val appContext: Context
) : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {
        if ((event as? AuthEvent.GetBioAuthSupportState)?.isSupported == false
            || state.screenStatus != AuthState.AuthScreenStatus.LOG_IN
            || !state.loginState.isPINUnlocked) return AuthEvent.None

        return when (biometricAuthManager.auth()) {
            null -> AuthEvent.SucceededToAuth
            BiometricPrompt.ERROR_LOCKOUT -> AuthEvent.Error(errorMessage = appContext.getString(R.string.error_to_many_attempts))
            else -> AuthEvent.None
        }
    }

    override fun canHandle(event: AuthEvent): Boolean =
        event is AuthEvent.AuthWithBio || event is AuthEvent.GetBioAuthSupportState
}