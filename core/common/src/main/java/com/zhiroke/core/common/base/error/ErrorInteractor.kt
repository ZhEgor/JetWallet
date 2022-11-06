package com.zhiroke.core.common.base.error

import com.zhiroke.core.common.base.BaseEvent

interface ErrorInteractor {

    suspend fun tryCatch(block: suspend () -> BaseEvent): BaseEvent {
        return try {
            block.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            ErrorEvent(message = e.message)
        }
    }
}

class ShowErrorInteractor {

}