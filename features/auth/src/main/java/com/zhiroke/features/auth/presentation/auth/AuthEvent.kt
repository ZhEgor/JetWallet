package com.zhiroke.features.auth.presentation.auth

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.error.ErrorEvent
import com.zhiroke.core.components.popup.PopUpState
import com.zhiroke.features.auth.presentation.auth.interactors.PINKeyboardCommand


internal sealed interface AuthEvent : BaseEvent {
    object None : AuthEvent
    object Load : AuthEvent
    class Error(override val errorMessage: String?, val durationInMillis: Long = 3000) : AuthEvent, ErrorEvent
    class LoadedPIN(val pin: String?) : AuthEvent
    class LoadedUnlockAttemptTime(val time: Long) : AuthEvent
    class GetBioAuthSupportState(val isSupported: Boolean) : AuthEvent
    object DropAllData : AuthEvent
    object ConfirmPIN : AuthEvent
    object AuthWithBio : AuthEvent
    class ExecuteKeyboardCommand(val command: PINKeyboardCommand) : AuthEvent
    class ChangePIN(val newPIN: String) : AuthEvent
    class ChangeDropDataPopUpState(val popUpState: PopUpState) : AuthEvent
    class ChangeLeftSecondsToBeUnlocked(val leftSeconds: Int?) : AuthEvent
    object SucceededToAuth : AuthEvent
    object FailedPINAuth : AuthEvent
    object FailedToConfirmPIN : AuthEvent
    class ChangeScreenStatus(val screenStatus: AuthState.AuthScreenStatus) : AuthEvent
}
