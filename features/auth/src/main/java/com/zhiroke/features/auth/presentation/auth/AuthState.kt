package com.zhiroke.features.auth.presentation.auth

import com.zhiroke.core.common.base.BaseState
import com.zhiroke.core.common.base.error.ErrorState
import com.zhiroke.core.components.popup.PopUpState


internal data class AuthState(
    val isLoading: Boolean,
    val loginState: LoginState,
    val setPINState: SetPINState,
    val confirmPINState: ConfirmPINState,
    val screenStatus: AuthScreenStatus,
    override val errorMessage: String?,
) : BaseState, ErrorState {

    internal data class LoginState(
        val attempt: Int,
        val unlockAttemptTime: Long,
        val secondsLeftToBeUnlocked: Int?,
        val loadedPIN: String?,
        val dropDataPopUpState: PopUpState,
        val pin: String,
        val isBioSupported: Boolean,
    ) {

        val isPINUnlocked get() = unlockAttemptTime <= System.currentTimeMillis()

        companion object {

            fun initialState() = LoginState(
                attempt = 0,
                unlockAttemptTime = 0,
                secondsLeftToBeUnlocked = null,
                pin = "",
                loadedPIN = null,
                dropDataPopUpState = PopUpState(isShown = false),
                isBioSupported = false
            )

            const val ATTEMPTS_LIMIT = 5
            const val LOCK_DELAY = 30 * 1000 // 30 seconds
        }
    }

    internal data class SetPINState(
        val pin: String
    ) {

        companion object {

            fun initialState() = SetPINState(
                pin = ""
            )
        }
    }

    internal data class ConfirmPINState(
        val pin: String
    ) {

        companion object {

            fun initialState() = ConfirmPINState(
                pin = "",
            )
        }
    }

    internal enum class AuthScreenStatus {
        LOG_IN, SET_PIN, CONFIRM_PIN
    }

    companion object {

        fun initialState() = AuthState(
            isLoading = true,
            loginState = LoginState.initialState(),
            setPINState = SetPINState.initialState(),
            confirmPINState = ConfirmPINState.initialState(),
            screenStatus = AuthScreenStatus.LOG_IN,
            errorMessage = null,
        )
    }
}