package com.zhiroke.core.common.base

interface BaseReducer<State : BaseState> {

    val initialState: State

    fun reduce(state: State, event: BaseEvent): State
}