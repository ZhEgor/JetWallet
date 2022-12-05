package com.zhiroke.core.common.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State: BaseState, Event : BaseEvent>(
    private val reducer: BaseReducer<State>,
    private val interactors: Set<BaseInteractor<State, Event>>
) : ViewModel() {

    private val currentSubscriptions: HashMap<SubscriptionInteractor<State, Event>, Job> = hashMapOf()
    private val _state = MutableStateFlow(reducer.initialState)
    val state: StateFlow<State> get() = _state

    @Suppress("UNCHECKED_CAST")
    protected fun sendEvent(event: Event) {
        // ToDo: add async
        _state.update { state -> reducer.reduce(state = state, event = event) }
        viewModelScope.launch {
            val deferred = mutableListOf<Deferred<Unit>>()
            interactors.filter { interactor -> interactor.canHandle(event = event) }.forEach { interactor ->
                when(interactor) {
                    is SuspendableInteractor<State, Event> -> deferred.add(
                        async(Dispatchers.IO) {
                            sendEvent(event = interactor.invoke(state = _state.value, event = event) as Event)
                        }
                    )
                    is SubscriptionInteractor<State, Event> -> {
                        currentSubscriptions[interactor]?.cancel() // prevent redundant subscription
                        Log.d("JET_TAG", "scope canceled: ${currentSubscriptions[interactor]}") // ToDo: investigate,
                        currentSubscriptions[interactor] = launch(Dispatchers.IO) {
                            interactor.invoke(state = _state.value, event = event).collectLatest { event ->
                                sendEvent(event = event as Event)
                                Log.d("JET_TAG", "event!!!! is active: ${this.isActive}, scope: $this") // ToDo: investigate,
                            }
                        }
                    }
                }
                Log.d("JET_TAG", "${currentSubscriptions.keys.map { it::class.java.name }.joinToString()}") // prevent redundant subscription
            }
            Log.d("JET_TAG", "sendEvent(event = $event)")
            deferred.forEach { it.await() }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}