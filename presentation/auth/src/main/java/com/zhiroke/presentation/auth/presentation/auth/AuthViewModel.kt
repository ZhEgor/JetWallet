package com.zhiroke.presentation.auth.presentation.auth

import androidx.annotation.RestrictTo
import com.zhiroke.core.common.base.BaseViewModel
import com.zhiroke.core.common.coroutines.AppDispatchersProvider
import com.zhiroke.core.common.coroutines.DispatchersProvider
import com.zhiroke.presentation.auth.presentation.auth.interactors.*


internal class AuthViewModel(
    reducer: AuthReducer,
    dispatchers: DispatchersProvider,
    keyboardHandlerInteractor: KeyboardHandlerInteractor?,
    biometricAuthInteractor: BiometricAuthInteractor?,
    checkForBioAuthSupportInteractor: CheckForBioAuthSupportInteractor?,
    succeededToAuthInteractor: SucceededToAuthInteractor?,
    confirmPINInteractor: ConfirmPINInteractor?,
    getPINInteractor: GetPINInteractor?,
    pinAuthInteractor: PINAuthInteractor?,
    authErrorStopperInteractor: AuthErrorStopperInteractor?,
    getUnlockPINTimeInteractor: GetUnlockPINTimeInteractor?,
    handlePINAuthFailureInteractor: HandlePINAuthFailureInteractor?,
    handleFailedToConfirmPINInteractor: HandleFailedToConfirmPINInteractor?,
    dropAllDataInteractor: DropAllDataInteractor?
) : BaseViewModel<AuthState, AuthEvent>(
    dispatchers = dispatchers,
    reducer = reducer,
    interactors = setOfNotNull(keyboardHandlerInteractor, biometricAuthInteractor,
        checkForBioAuthSupportInteractor, succeededToAuthInteractor, confirmPINInteractor,
        getPINInteractor, pinAuthInteractor, authErrorStopperInteractor, getUnlockPINTimeInteractor,
        handlePINAuthFailureInteractor, handleFailedToConfirmPINInteractor, dropAllDataInteractor
    ),
    initialEvent = AuthEvent.Load
) {

    fun executeCommand(command: PINKeyboardCommand) =
        sendEvent(AuthEvent.ExecuteKeyboardCommand(command = command))

    fun authWithBio(command: PINKeyboardCommand) = sendEvent(AuthEvent.AuthWithBio)

    fun changeScreenStatusToSetPIN() = sendEvent(AuthEvent.ChangeScreenStatus(screenStatus = AuthState.AuthScreenStatus.SET_PIN))

    fun changeScreenStatusToConfirmPIN() = sendEvent(AuthEvent.ChangeScreenStatus(screenStatus = AuthState.AuthScreenStatus.CONFIRM_PIN))

    fun confirmPIN() = sendEvent(AuthEvent.ConfirmPIN)

    fun hideDropDataPopUp() = sendEvent(AuthEvent.ChangeDropDataPopUpState(state.value.loginState.dropDataPopUpState.copy(isShown = false)))

    fun showDropDataPopUp() = sendEvent(AuthEvent.ChangeDropDataPopUpState(state.value.loginState.dropDataPopUpState.copy(isShown = true)))

    fun dropAllData() = sendEvent(AuthEvent.DropAllData)
}

/** This function is used only for compose preview. */
@RestrictTo(RestrictTo.Scope.TESTS)
internal fun getDummyAuthViewModel(): AuthViewModel {
    return AuthViewModel(
        dispatchers = AppDispatchersProvider(),
        reducer = AuthReducer(),
        keyboardHandlerInteractor = null,
        biometricAuthInteractor = null,
        checkForBioAuthSupportInteractor = null,
        succeededToAuthInteractor = null,
        confirmPINInteractor = null,
        getPINInteractor = null,
        pinAuthInteractor = null,
        authErrorStopperInteractor = null,
        getUnlockPINTimeInteractor = null,
        handlePINAuthFailureInteractor = null,
        handleFailedToConfirmPINInteractor = null,
        dropAllDataInteractor = null
    )
}