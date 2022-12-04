package com.zhiroke.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BankCardLocal.TABLE_NAME)
data class BankCardLocal(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID) val id: String,
    @ColumnInfo(name = COLUMN_NUMBER) val number: String,
    @ColumnInfo(name = COLUMN_CARDHOLDER_NAME) val cardholderName: String,
    @ColumnInfo(name = COLUMN_EXPIRATION_DATE) val expirationDate: String,
    @ColumnInfo(name = COLUMN_VERIFICATION_NUMBER) val verificationNumber: String,
) {

    internal companion object {

        const val TABLE_NAME = "bank_card"

        const val COLUMN_ID = "id"
        const val COLUMN_NUMBER = "number"
        const val COLUMN_CARDHOLDER_NAME = "cardholder_name"
        const val COLUMN_EXPIRATION_DATE = "expiration_date"
        const val COLUMN_VERIFICATION_NUMBER = "verification_number"
    }
}
