package com.zhiroke.core.common.base.error

import com.zhiroke.core.common.base.BaseEvent

data class ErrorEvent(
    val message: String?
) : BaseEvent