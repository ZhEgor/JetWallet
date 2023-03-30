package com.zhiroke.features.auth.presentation.auth.interactors

import android.content.Context
import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SubscriptionInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.core.common.utils.vibrate
import com.zhiroke.domain.repository.user.UserDataRepository
import com.zhiroke.features.auth.presentation.auth.AuthEvent
import com.zhiroke.features.auth.presentation.auth.AuthState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class HandlePINAuthFailureInteractor(
    private val userDataRepository: UserDataRepository,
    private val appContext: Context
) : SubscriptionInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): Flow<BaseEvent> {
        appContext.vibrate()
        return if (!state.loginState.isPINUnlocked) {
            userDataRepository.setUnlockTime(state.loginState.unlockAttemptTime)
            flow {
                while (!state.loginState.isPINUnlocked) {
                    delay(1000)
                    val leftSeconds = ((state.loginState.unlockAttemptTime - System.currentTimeMillis()) / 1000).toInt()
                    emit(AuthEvent.ChangeLeftSecondsToBeUnlocked(leftSeconds = leftSeconds))
                }
                emit(AuthEvent.ChangeLeftSecondsToBeUnlocked(leftSeconds = null))
            }
        } else flowOf(AuthEvent.None)
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.FailedPINAuth
            || event is AuthEvent.LoadedUnlockAttemptTime
}
