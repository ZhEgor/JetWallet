package com.zhiroke.core.common.base.error

import com.zhiroke.core.common.base.BaseEvent

interface ErrorInteractor {

    /**
     * Executes a block, in case of failure, we handle it with a defined or default handler
     */
    suspend fun tryCatch(onFailureHandler: ((e: Exception) -> ErrorEvent)? = null, block: suspend () -> BaseEvent): BaseEvent {
        return try {
            block.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            onFailureHandler?.invoke(e) ?: DefaultErrorEvent(errorMessage = e.message)
        }
    }
}