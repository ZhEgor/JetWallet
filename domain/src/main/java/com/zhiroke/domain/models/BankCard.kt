package com.zhiroke.domain.models

import com.zhiroke.data.models.BankCardLocal

data class BankCard(
    internal val id: String,
    val number: String,
    val cardholderName: String,
    val expirationDate: String,
    val verificationNumber: String,
)

internal fun BankCard.toLocal() = BankCardLocal(
    id = id,
    number = number,
    cardholderName = cardholderName,
    expirationDate = expirationDate,
    verificationNumber = verificationNumber
)

internal fun BankCardLocal.toUi() = BankCard(
    id = id,
    number = number,
    cardholderName = cardholderName,
    expirationDate = expirationDate,
    verificationNumber = verificationNumber
)