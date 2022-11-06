package com.zhiroke.core.common.base

interface BaseInteractor<State : BaseState, in Event : BaseEvent> {

    suspend fun invoke(state: State, event: Event): BaseEvent

    fun canHandle(event: Event): Boolean
}