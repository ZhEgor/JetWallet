package com.zhiroke.features.cardrecognition.recognizer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal typealias Selection = List<List<Char>>

/**
 * @param pattern example: "xxxx xxxx xxxx xxxx".
 * @param iterationLimitMin with this amount of iteration we guarantee not to throw a best match
 * @param iterationLimitMax with this amount of iteration we guarantee to throw a best match
 */
internal abstract class Recognizer(
    protected val pattern: String,
    protected val iterationLimitMin: Int,
    protected val iterationLimitMax: Int,
    protected val onSuccess: (String) -> Unit
) {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    private val fedSelection: MutableStateFlow<Selection> = MutableStateFlow(listOf())
    protected var currentIteration = 0
    private var isFeedingStopped = false

    init {
        observeSelection()
    }

    fun feed(text: String) {
        if (isFeedingStopped) return
        if (matchesPattern(text)) {
            fedSelection.update { selection ->
                selection.plus(listOf(text.toList()))
            }
        }
    }

    protected abstract fun findBestMatch(selection: Selection): String

    private fun observeSelection() {
        scope.launch {
            fedSelection.collectLatest { selection ->
                currentIteration++
                findBestMatch(selection = selection).let { bestMatch ->
                    if ((bestMatch.length == pattern.length && currentIteration <= iterationLimitMin) || currentIteration >= iterationLimitMax) {
                        isFeedingStopped = true
                        bestMatch.filterNot(pattern::contains).let(onSuccess)
                        return@collectLatest
                    }
                }
            }
        }
    }

    private fun matchesPattern(text: String): Boolean {
        if (text.length != pattern.length) return false
        for (i in pattern.indices) { // check for a pattern
            if (pattern[i] != DIGIT_SYMBOL && text[i] != pattern[i]) return false
        }
        return true
    }

    companion object {

        const val DIGIT_SYMBOL = 'x'
    }
}
