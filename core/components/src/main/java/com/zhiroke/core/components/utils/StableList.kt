package com.zhiroke.core.components.utils

import androidx.compose.runtime.Stable

@Stable
class StableList<T>(private val list: List<T>) : List<T> by list {

    override fun hashCode(): Int {
        return list.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return (other as? StableList<T>)?.list?.let {
            it == list
        } ?: false
    }
}

fun <T> List<T>.toStableList(): StableList<T> = StableList(list = this)

fun <T> emptyStableList(): StableList<T> = StableList(emptyList())

fun <T> stableListOf(): StableList<T> = StableList(listOf())

fun <T> stableListOf(vararg elements: T): StableList<T> = StableList(listOf(*elements))
