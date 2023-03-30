package com.zhiroke.features.auth.presentation.auth

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.BaseReducer


internal class AuthReducer : BaseReducer<AuthState> {

    override val initialState: AuthState get() = AuthState.initialState()

    override fun reduce(state: AuthState, event: BaseEvent): AuthState {
        return state.run {
            when(event) {
                AuthEvent.None -> state
                AuthEvent.Load -> state
                AuthEvent.AuthWithBio -> state
                AuthEvent.SucceededToAuth -> state
                AuthEvent.ConfirmPIN -> state
                AuthEvent.DropAllData -> state
                AuthEvent.FailedPINAuth -> state.copy(
                    loginState = loginState.copy(
                        attempt = loginState.attempt + 1,
                        unlockAttemptTime = if (loginState.attempt + 1 >= AuthState.LoginState.ATTEMPTS_LIMIT) {
                            System.currentTimeMillis() + AuthState.LoginState.LOCK_DELAY
                        } else loginState.unlockAttemptTime,
                        pin = ""
                    )
                )
                AuthEvent.FailedToConfirmPIN -> state.copy(confirmPINState = confirmPINState.copy(pin = ""))
                is AuthEvent.Error -> state.copy(errorMessage = event.errorMessage)
                is AuthEvent.ChangeScreenStatus -> state.copy(screenStatus = event.screenStatus)
                is AuthEvent.LoadedPIN -> state.copy(
                    screenStatus = if(event.pin != null) AuthState.AuthScreenStatus.LOG_IN else AuthState.AuthScreenStatus.SET_PIN,
                    loginState = loginState.copy(loadedPIN = event.pin)
                )
                is AuthEvent.ChangeDropDataPopUpState -> state.copy(
                    loginState = loginState.copy(dropDataPopUpState = event.popUpState)
                )
                is AuthEvent.LoadedUnlockAttemptTime -> state.copy(loginState = loginState.copy(unlockAttemptTime = event.time))
                is AuthEvent.GetBioAuthSupportState -> state.copy(loginState = loginState.copy(isBioSupported = event.isSupported))
                is AuthEvent.ChangeLeftSecondsToBeUnlocked -> if (event.leftSeconds == null) {
                    // if we had waited for the blocking delay
                    state.copy(loginState = loginState.copy(attempt = 0, unlockAttemptTime = 0, secondsLeftToBeUnlocked = null))
                } else {
                    state.copy(loginState = loginState.copy(secondsLeftToBeUnlocked = event.leftSeconds))
                }
                is AuthEvent.ChangePIN -> when (state.screenStatus) {
                    AuthState.AuthScreenStatus.LOG_IN -> state.copy(loginState = loginState.copy(pin = event.newPIN))
                    AuthState.AuthScreenStatus.SET_PIN -> state.copy(setPINState = setPINState.copy(pin = event.newPIN))
                    AuthState.AuthScreenStatus.CONFIRM_PIN -> state.copy(confirmPINState = confirmPINState.copy(pin = event.newPIN))
                }
                is AuthEvent.ExecuteKeyboardCommand -> state
                else -> throw IllegalArgumentException("Unsupported Event: $event")
            }
        }
    }
}