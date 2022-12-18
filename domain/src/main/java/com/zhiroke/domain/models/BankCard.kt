package com.zhiroke.domain.models

import android.net.Uri
import androidx.core.net.toUri
import com.zhiroke.data.models.BankCardLocal

data class BankCard(
    internal val id: String,
    val number: String,
    val cardholderName: String,
    val expirationDate: String,
    val verificationNumber: String,
    val skinUri: Uri?,
) {

    companion object {

        fun empty() = BankCard(id = "", number = "", cardholderName = "", expirationDate = "", verificationNumber = "", skinUri = null)

        fun dummy() = BankCard(id = "1", number = "1234123412341234", cardholderName = "Egor Zhir", expirationDate = "1212", verificationNumber = "123", skinUri = null)
    }
}

internal fun BankCard.toLocal() = BankCardLocal(
    id = id,
    number = number,
    cardholderName = cardholderName,
    expirationDate = expirationDate,
    verificationNumber = verificationNumber,
    skinUri = skinUri?.toString(),
)

internal fun BankCardLocal.toUi() = BankCard(
    id = id,
    number = number,
    cardholderName = cardholderName,
    expirationDate = expirationDate,
    verificationNumber = verificationNumber,
    skinUri = skinUri?.toUri()
)