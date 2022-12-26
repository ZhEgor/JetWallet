package com.zhiroke.core.common.utils

/**
 * Use case:
 * val response: String? = getString()
 * val result = response?.let { handling } otherwise { handling }
 */
internal infix fun <T> T?.otherwise(block: () -> T): T {
    return this ?: block.invoke()
}