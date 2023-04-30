package com.zhiroke.presentation.auth.presentation.auth.interactors

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.domain.repository.user.UserDataRepository
import com.zhiroke.presentation.auth.presentation.auth.AuthEvent
import com.zhiroke.presentation.auth.presentation.auth.AuthState

internal class ConfirmPINInteractor(
    private val userDataRepository: UserDataRepository
) : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {
        return if (state.setPINState.pin == state.confirmPINState.pin) {
            userDataRepository.setPIN(pin = state.confirmPINState.pin)
            AuthEvent.SucceededToAuth
        } else {
            AuthEvent.FailedToConfirmPIN
        }
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.ConfirmPIN
}