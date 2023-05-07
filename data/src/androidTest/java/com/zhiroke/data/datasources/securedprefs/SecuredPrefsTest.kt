package com.zhiroke.data.datasources.securedprefs

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.zhiroke.data.di.modules.provideEncryptedSharedPreferences
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class SecuredPrefsTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val encryptedSharedPreferences = provideEncryptedSharedPreferences(context)
    private val sharedPreferences = context.getSharedPreferences(
        "secured_user_prefs",
        Context.MODE_PRIVATE
    )

    @Test
    fun reading_of_encrypted_data_failed() {
        val securedPref: SecuredPrefs = SecuredPrefsImpl(prefs = encryptedSharedPreferences)
        val commonPrefs: SecuredPrefs = SecuredPrefsImpl(prefs = sharedPreferences)
        val pin = "1234"
        runBlocking {
            securedPref.setPIN(pin = pin)
            assertThat(commonPrefs.getPIN()).isNotEqualTo(pin)
        }
    }

    @Test
    fun reading_of_decrypted_data_succeeded() {
        val securedPref: SecuredPrefs = SecuredPrefsImpl(prefs = encryptedSharedPreferences)
        val pin = "1234"
        runBlocking {
            securedPref.setPIN(pin = pin)
            assertThat(securedPref.getPIN()).isEqualTo(pin)
        }
    }
}