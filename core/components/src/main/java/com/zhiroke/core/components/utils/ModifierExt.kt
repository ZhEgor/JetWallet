package com.zhiroke.core.components.utils

import androidx.compose.ui.Modifier


fun Modifier.ifThen(predicate: () -> Boolean, other: Modifier.() -> Modifier): Modifier {
    return if (predicate.invoke()) {
        other.invoke(this)
    } else this
}

fun Modifier.ifThen(predicate: Boolean, other: Modifier.() -> Modifier): Modifier {
    return if (predicate) {
        other.invoke(this)
    } else this
}