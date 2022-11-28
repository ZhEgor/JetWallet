package com.zhiroke.core.common.utils


inline fun <T> T.ifThen(predicate: () -> Boolean, other: T.() -> T): T {
    return if (predicate.invoke()) {
        other.invoke(this)
    } else this
}

inline fun <T> T.ifThen(predicate: Boolean, other: T.() -> T): T {
    return if (predicate) {
        other.invoke(this)
    } else this
}

inline fun <T, R> R.ifNotNullThen(value: T?, other: R.(T) -> R): R {
    return if (value != null) {
        other.invoke(this, value)
    } else this
}