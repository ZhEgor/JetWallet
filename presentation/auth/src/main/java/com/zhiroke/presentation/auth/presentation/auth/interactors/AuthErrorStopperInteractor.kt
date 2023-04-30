package com.zhiroke.presentation.auth.presentation.auth.interactors

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.presentation.auth.presentation.auth.AuthEvent
import com.zhiroke.presentation.auth.presentation.auth.AuthState
import kotlinx.coroutines.delay

internal class AuthErrorStopperInteractor : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {
        if (event !is AuthEvent.Error || event.errorMessage == null) return AuthEvent.None
        delay(event.durationInMillis)
        return AuthEvent.Error(errorMessage = null)
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.Error
}