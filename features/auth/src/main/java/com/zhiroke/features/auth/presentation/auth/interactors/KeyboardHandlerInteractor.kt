package com.zhiroke.features.auth.presentation.auth.interactors

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.features.auth.presentation.auth.AuthEvent
import com.zhiroke.features.auth.presentation.auth.AuthState

internal sealed interface PINKeyboardCommand {
    object RemoveCharacter : PINKeyboardCommand
    class WriteCharacter(val character: String) : PINKeyboardCommand
    object AuthWithBio : PINKeyboardCommand
}

internal class KeyboardHandlerInteractor : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {

        if (!state.loginState.isPINUnlocked) return AuthEvent.None

        val password = when (state.screenStatus) {
            AuthState.AuthScreenStatus.LOG_IN -> state.loginState.pin
            AuthState.AuthScreenStatus.SET_PIN -> state.setPINState.pin
            AuthState.AuthScreenStatus.CONFIRM_PIN -> state.confirmPINState.pin
        }

        return when (val command = (event as AuthEvent.ExecuteKeyboardCommand).command) {
            PINKeyboardCommand.RemoveCharacter -> {
                AuthEvent.ChangePIN(newPIN = password.dropLast(1))
            }
            is PINKeyboardCommand.WriteCharacter -> {
                val newPassword = if (password.length >= 4) password else password + command.character
                AuthEvent.ChangePIN(newPIN = newPassword)
            }
            else -> AuthEvent.None
        }
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.ExecuteKeyboardCommand
}