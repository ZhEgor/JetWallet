package com.zhiroke.core.common.base

import kotlinx.coroutines.flow.Flow

interface BaseInteractor<in State : BaseState, in Event: BaseEvent> {

    fun canHandle(event: Event): Boolean
}

interface SuspendableInteractor<in State : BaseState, in Event : BaseEvent>: BaseInteractor<State, Event> {

    suspend fun invoke(state: State, event: Event): BaseEvent
}

interface SubscriptionInteractor<in State : BaseState, in Event : BaseEvent> : BaseInteractor<State, Event> {

    suspend fun invoke(state: State, event: Event): Flow<BaseEvent>
}