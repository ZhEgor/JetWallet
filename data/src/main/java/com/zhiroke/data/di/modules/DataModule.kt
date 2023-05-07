package com.zhiroke.data.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.zhiroke.data.datasources.keystore.CryptoManager
import com.zhiroke.data.datasources.room.dao.BankCardDao
import com.zhiroke.data.datasources.room.database.WALLET_DATABASE_NAME
import com.zhiroke.data.datasources.room.database.WalletDatabase
import com.zhiroke.data.datasources.securedprefs.SecuredPrefs
import com.zhiroke.data.datasources.securedprefs.SecuredPrefsImpl
import com.zhiroke.data.util.PasswordProvider
import net.sqlcipher.database.SupportFactory
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun dataModule() = module {

    factoryOf(::provideWalletRoomDatabase)
    factoryOf(::provideBankCardDao)
    factoryOf(::provideEncryptedSharedPreferences)
    factoryOf(::CryptoManager)
    factoryOf(::PasswordProvider)
    factoryOf(::SecuredPrefsImpl) { bind<SecuredPrefs>() }

}

internal fun provideWalletRoomDatabase(context: Context, passwordProvider: PasswordProvider): WalletDatabase {
    val password = passwordProvider.getPassword(fileName = "wallet_db_password.bin")
    return Room.databaseBuilder(context, WalletDatabase::class.java, WALLET_DATABASE_NAME).run {
        openHelperFactory(SupportFactory(password))
        build()
    }
}

private fun provideBankCardDao(walletDatabase: WalletDatabase): BankCardDao {
    return walletDatabase.getBankCardDao()
}

internal fun provideEncryptedSharedPreferences(context: Context): SharedPreferences {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    return EncryptedSharedPreferences.create(
        context,
        "secured_user_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}