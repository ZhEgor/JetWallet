package com.zhiroke.core.common.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: BaseState, Event : BaseEvent>(
    private val reducer: BaseReducer<State>,
    private val interactors: Set<BaseInteractor<State, Event>>
) : ViewModel() {

    private val _state = MutableStateFlow(reducer.initialState)
    val state: StateFlow<State> get() = _state

    protected fun sendEvent(event: Event) {
        // ToDo: add async
        _state.update { state -> reducer.reduce(state = state, event = event) }
        viewModelScope.launch {
            val deferred = mutableListOf<Deferred<Unit>>()
            interactors.filter { interactor -> interactor.canHandle(event = event) }.forEach { interactor ->
                deferred.add(
                    async(Dispatchers.IO) {
                        sendEvent(event = interactor.invoke(state = _state.value, event = event) as Event)
                    }
                )
            }
            Log.d("JET_TAG", "sendEvent(event = $event)")
            deferred.forEach { it.await() }
        }
    }
}