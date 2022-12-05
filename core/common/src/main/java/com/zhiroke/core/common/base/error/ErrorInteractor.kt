package com.zhiroke.core.common.base.error

import com.zhiroke.core.common.base.BaseEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface ErrorInteractor<Event> {

    suspend fun tryCatch(
        onFailureHandler: ((e: Exception) -> ErrorEvent)? = null,
        block: suspend () -> Event
    ): Event
}

interface SuspendableErrorInteractor : ErrorInteractor<BaseEvent> {

    /**
     * Executes a block, in case of failure, we handle it with a defined or default handler
     */
    override suspend fun tryCatch(
        onFailureHandler: ((e: Exception) -> ErrorEvent)?,
        block: suspend () -> BaseEvent
    ): BaseEvent {
        return try {
            block.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            onFailureHandler?.invoke(e) ?: DefaultErrorEvent(errorMessage = e.message)
        }
    }
}

interface SubscriptionErrorInteractor : ErrorInteractor<Flow<BaseEvent>> {

    /**
     * Wrapping in result in Flow, implementation for @see SubscriptionInteractor
     */
    override suspend fun tryCatch(
        onFailureHandler: ((e: Exception) -> ErrorEvent)?,
        block: suspend () -> Flow<BaseEvent>
    ): Flow<BaseEvent> {
        return try {
            block.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            flowOf(onFailureHandler?.invoke(e) ?: DefaultErrorEvent(errorMessage = e.message))
        }
    }
}
