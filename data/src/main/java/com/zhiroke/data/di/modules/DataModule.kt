package com.zhiroke.data.di.modules

import androidx.room.Room
import com.zhiroke.data.room.dao.BankCardDao
import com.zhiroke.data.room.database.WALLET_DATABASE_NAME
import com.zhiroke.data.room.database.WalletDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun dataModule(password: String = "password") = module {

    factory {
        Room.databaseBuilder(androidContext(), WalletDatabase::class.java, WALLET_DATABASE_NAME).run {
            openHelperFactory(SupportFactory(password.toByteArray()))
            build()
        }
    }

    factoryOf(::provideBankCardDao)
}

private fun provideBankCardDao(walletDatabase: WalletDatabase): BankCardDao {
    return walletDatabase.getBankCardDao()
}