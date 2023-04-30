package com.zhiroke.presentation.auth.presentation.auth.interactors

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.presentation.auth.presentation.auth.AuthEvent
import com.zhiroke.presentation.auth.presentation.auth.AuthState
import com.zhiroke.presentation.auth.utils.managers.BiometricAuthManager

internal class CheckForBioAuthSupportInteractor(
    private val biometricAuthManager: BiometricAuthManager
) : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {
        val result = biometricAuthManager.isBioAuthSupported()
        return AuthEvent.GetBioAuthSupportState(isSupported = result)
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.LoadedPIN
}