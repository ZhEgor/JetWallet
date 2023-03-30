package com.zhiroke.features.auth.presentation.auth.interactors

import android.content.Context
import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.features.auth.R
import com.zhiroke.features.auth.presentation.auth.AuthEvent
import com.zhiroke.features.auth.presentation.auth.AuthState

internal class HandleFailedToConfirmPINInteractor(
    private val appContext: Context
) : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {
        return AuthEvent.Error(errorMessage = appContext.getString(R.string.error_pins_dont_match))
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.FailedToConfirmPIN
}
