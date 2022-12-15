package com.zhiroke.features.mywallet.utils

import com.zhiroke.domain.models.BankCard


internal fun String.isNumberValid(): Boolean {
    return length == 16 && all { it.isDigit() }
}

internal fun String.isExpirationDateValid(): Boolean {
    return length == 4 && all { it.isDigit() } && take(2).toInt() in (1..12)
}

internal fun String.isCardholderNameValid(): Boolean {
    return all { it.isLetter() || it.isWhitespace() } && isNotBlank()
}

internal fun String.isVerificationNumberValid(): Boolean {
    return length in (3..4) && all { it.isDigit() }
}

internal fun BankCard.isValid(): Boolean {
    return number.isNumberValid() &&
            expirationDate.isExpirationDateValid() &&
            cardholderName.isCardholderNameValid() &&
            verificationNumber.isVerificationNumberValid()
}
