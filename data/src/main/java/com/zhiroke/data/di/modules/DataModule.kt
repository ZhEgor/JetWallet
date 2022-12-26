package com.zhiroke.data.di.modules

import android.content.Context
import androidx.room.Room
import com.zhiroke.data.keystore.CryptoManager
import com.zhiroke.data.room.dao.BankCardDao
import com.zhiroke.data.room.database.WALLET_DATABASE_NAME
import com.zhiroke.data.room.database.WalletDatabase
import com.zhiroke.data.util.PasswordProvider
import net.sqlcipher.database.SupportFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun dataModule() = module {

    factoryOf(::provideWalletRoomDatabase)
    factoryOf(::provideBankCardDao)
    factoryOf(::CryptoManager)
    factoryOf(::PasswordProvider)

}

private fun provideWalletRoomDatabase(context: Context, passwordProvider: PasswordProvider): WalletDatabase {
    val password = passwordProvider.getPassword(fileName = "wallet_db_password.bin")
    return Room.databaseBuilder(context, WalletDatabase::class.java, WALLET_DATABASE_NAME).run {
        openHelperFactory(SupportFactory(password))
        build()
    }
}

private fun provideBankCardDao(walletDatabase: WalletDatabase): BankCardDao {
    return walletDatabase.getBankCardDao()
}