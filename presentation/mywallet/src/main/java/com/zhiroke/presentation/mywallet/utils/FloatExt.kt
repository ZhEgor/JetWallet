package com.zhiroke.presentation.mywallet.utils


infix fun Float.roundUpToMultipleOf(multiplier: Float): Float {
    val diff = this % multiplier
    return if (multiplier / 2 > diff) {
        this - diff
    } else {
        this + multiplier - diff
    }
}

fun Float.reverseSignIf(predicate: Boolean): Float = if (predicate) this * -1 else this
