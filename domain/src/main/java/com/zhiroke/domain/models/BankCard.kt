package com.zhiroke.domain.models

data class BankCard(
    val number: String,
    val cardholderName: String,
    val expirationDate: String,
    val verificationNumber: String,
)
