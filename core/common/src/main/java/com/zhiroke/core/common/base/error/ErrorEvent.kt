package com.zhiroke.core.common.base.error

import com.zhiroke.core.common.base.BaseEvent

interface ErrorEvent : BaseEvent {
    val errorMessage: String?
}

data class DefaultErrorEvent(
    override val errorMessage: String?
) : ErrorEvent