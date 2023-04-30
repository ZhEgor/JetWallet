package com.zhiroke.core.cardrecognition.recognizer

import android.util.Log


internal class DigitRecognizer(
    pattern: String,
    iterationLimitMin: Int = 5,
    iterationLimitMax: Int = 10,
    onSuccess: (String) -> Unit
) : Recognizer(
    pattern = pattern,
    iterationLimitMin = iterationLimitMin,
    iterationLimitMax = iterationLimitMax,
    onSuccess = onSuccess
) {

    override fun findBestMatch(selection: Selection): String {
        val unrecognizedSymbol = DIGIT_SYMBOL
        val bestMatch = CharArray(pattern.length) { unrecognizedSymbol }

        for (i in pattern.indices) {
            if (pattern[i] == DIGIT_SYMBOL) {
                val bestMatchesForSymbol: HashMap<Char, Int> = hashMapOf()
                for (j in selection.indices) {
                    val matchingSymbol = selection[j][i]
                    if (matchingSymbol.isDigit()) {
                        bestMatchesForSymbol[matchingSymbol] = bestMatchesForSymbol[matchingSymbol]?.let { it + 1 } ?: 1 // creating selection of repeating ones
                    }
                }
                bestMatchesForSymbol.maxByOrNull { it.value }?.key?.let { symbol ->
                    bestMatch[i] = symbol
                }
            } else {
                bestMatch[i] = pattern[i]
            }
        }

        return bestMatch.filterNot { symbol -> symbol == unrecognizedSymbol }.joinToString("").also {
            Log.d("JET_TAG", "$currentIteration. potential best match: $it")
        }
    }
}
