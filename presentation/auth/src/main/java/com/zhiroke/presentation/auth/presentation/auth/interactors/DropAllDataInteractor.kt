package com.zhiroke.presentation.auth.presentation.auth.interactors

import com.zhiroke.core.common.base.BaseEvent
import com.zhiroke.core.common.base.SuspendableInteractor
import com.zhiroke.core.common.base.error.SuspendableErrorInteractor
import com.zhiroke.core.navigation.directions.AuthNavDirections
import com.zhiroke.core.navigation.domain.NavigationManager
import com.zhiroke.domain.repository.user.UserDataRepository
import com.zhiroke.presentation.auth.presentation.auth.AuthEvent
import com.zhiroke.presentation.auth.presentation.auth.AuthState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class DropAllDataInteractor(
    private val userDataRepository: UserDataRepository,
    private val navigationManager: NavigationManager
) : SuspendableInteractor<AuthState, AuthEvent>, SuspendableErrorInteractor {

    override suspend fun invoke(state: AuthState, event: AuthEvent): BaseEvent {
        userDataRepository.dropData()
        CoroutineScope(Dispatchers.Main).launch {
            navigationManager.navigateTo(direction = AuthNavDirections.auth) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
        return AuthEvent.None
    }

    override fun canHandle(event: AuthEvent): Boolean = event is AuthEvent.DropAllData
}