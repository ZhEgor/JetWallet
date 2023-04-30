package com.zhiroke.presentation.auth.di.modules

import com.zhiroke.presentation.auth.presentation.auth.interactors.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


internal fun interactorsModule() = module {

    factoryOf(::KeyboardHandlerInteractor)
    factoryOf(::BiometricAuthInteractor)
    factoryOf(::CheckForBioAuthSupportInteractor)
    factoryOf(::SucceededToAuthInteractor)
    factoryOf(::ConfirmPINInteractor)
    factoryOf(::GetPINInteractor)
    factoryOf(::GetUnlockPINTimeInteractor)
    factoryOf(::PINAuthInteractor)
    factoryOf(::AuthErrorStopperInteractor)
    factoryOf(::HandlePINAuthFailureInteractor)
    factoryOf(::HandleFailedToConfirmPINInteractor)
    factoryOf(::DropAllDataInteractor)

}