package com.zhiroke.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhiroke.core.common.coroutines.DispatchersProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update

abstract class BaseViewModel<State: BaseState, Event : BaseEvent>(
    private val dispatchers: DispatchersProvider,
    private val reducer: BaseReducer<State>,
    private val interactors: Set<BaseInteractor<State, Event>>,
    initialEvent: Event
) : ViewModel() {

    private val currentSubscriptions: HashMap<SubscriptionInteractor<State, Event>, Job> = hashMapOf()
    private val _state = MutableStateFlow(reducer.initialState)
    val state: StateFlow<State> get() = _state

    init {
        sendEvent(event = initialEvent)
    }

    @Suppress("UNCHECKED_CAST")
    protected fun sendEvent(event: Event) {
        _state.update { state -> reducer.reduce(state = state, event = event) }
        viewModelScope.launch {
            val deferred = mutableListOf<Deferred<Unit>>()
            interactors.filter { interactor -> interactor.canHandle(event = event) }.forEach { interactor ->
                when(interactor) {
                    is SuspendableInteractor<State, Event> -> deferred.add(
                        async(dispatchers.io) {
                            sendEvent(event = interactor.invoke(state = _state.value, event = event) as Event)
                        }
                    )
                    is SubscriptionInteractor<State, Event> -> {
                        currentSubscriptions[interactor]?.cancel() // prevent redundant subscription
                        currentSubscriptions[interactor] = launch(dispatchers.io) {
                            interactor.invoke(state = _state.value, event = event).collectLatest { event ->
                                sendEvent(event = event as Event)
                            }
                        }
                    }
                }
            }
            deferred.forEach { it.await() }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}