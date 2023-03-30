package com.zhiroke.data.datasources.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zhiroke.data.models.BankCardLocal
import com.zhiroke.data.datasources.room.dao.BankCardDao

internal const val WALLET_DATABASE_NAME = "wallet_database"
private const val WALLET_DATABASE_VERSION = 1

@Database(
    entities = [BankCardLocal::class],
    version = WALLET_DATABASE_VERSION,
    exportSchema = true,
)
internal abstract class WalletDatabase : RoomDatabase() {

    abstract fun getBankCardDao(): BankCardDao
}
