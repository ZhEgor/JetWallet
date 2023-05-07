package com.zhiroke.data.datasources.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.zhiroke.data.datasources.keystore.CryptoManager
import com.zhiroke.data.datasources.room.database.WALLET_DATABASE_NAME
import com.zhiroke.data.datasources.room.database.WalletDatabase
import com.zhiroke.data.di.modules.provideWalletRoomDatabase
import com.zhiroke.data.models.BankCardLocal
import com.zhiroke.data.util.PasswordProvider
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WalletDatabaseTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val passwordProvider = PasswordProvider(context = context, cryptoManager = CryptoManager())
    private val securedDatabase = provideWalletRoomDatabase(
        context = context,
        passwordProvider = passwordProvider
    )

    @Test
    fun reading_of_encrypted_data_failed() {
        val securedBankCardDao = securedDatabase.getBankCardDao()
        val commonBankCardDao = Room.databaseBuilder(context, WalletDatabase::class.java, WALLET_DATABASE_NAME).build().getBankCardDao()
        val bankCard = BankCardLocal("1", "1", "1", "1","1", "1")
        runBlocking {
            securedBankCardDao.insert(entity = bankCard)
            commonBankCardDao.readAll().firstOrNull()?.forEach { readBankCard ->
                if (readBankCard == bankCard) {
                    assertThat(true).isFalse()
                    return@runBlocking
                }
            }
            assertThat(false).isFalse()
        }
    }

    @Test
    fun reading_of_decrypted_data_succeeded() {
        val securedBankCardDao = securedDatabase.getBankCardDao()
        val bankCard = BankCardLocal("2", "2", "2", "2","2", "2")
        runBlocking {
            securedBankCardDao.insert(entity = bankCard)
            val list = securedBankCardDao.readAll().firstOrNull()
            list?.forEach { readBankCard ->
                if (readBankCard == bankCard) {
                    assertThat(true).isTrue()
                    return@runBlocking
                }
            }
            assertThat(false).isTrue()
        }
    }

    @After
    fun deleteDatabase() {
        context.deleteDatabase(WALLET_DATABASE_NAME)
    }
}