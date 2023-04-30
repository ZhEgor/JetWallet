package com.zhiroke.presentation.auth.presentation.auth.interactors

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.domain.repository.user.UserDataRepository
import com.zhiroke.presentation.auth.presentation.auth.AuthEvent
import com.zhiroke.presentation.auth.presentation.auth.AuthState

internal class PINAuthInteractor(
    private val userDataRepository: UserDataRepository
) : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {
        if (state.loginState.pin.length != 4 || state.screenStatus != AuthState.AuthScreenStatus.LOG_IN) return AuthEvent.None
        val unlockTimeInMillis = userDataRepository.getUnlockTime()
        if (unlockTimeInMillis != null && unlockTimeInMillis > System.currentTimeMillis()) return AuthEvent.None

        return if (state.loginState.pin == state.loginState.loadedPIN) {
            AuthEvent.SucceededToAuth
        } else {
            AuthEvent.FailedPINAuth
        }
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.ChangePIN
}