package com.zhiroke.data.util

import android.content.Context
import com.zhiroke.data.datasources.keystore.KeyStoreHelper
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.SecureRandom

class PasswordProvider(private val context: Context, private val keyStoreHelper: KeyStoreHelper) {

    fun getPassword(fileName: String): ByteArray {
        val file = File(context.filesDir, fileName)
        return if (file.exists()) {
            keyStoreHelper.decrypt(inputStream = FileInputStream(file))
        } else {
            val password = generatePassword()
            keyStoreHelper.encrypt(bytes = password, outputStream = FileOutputStream(file))
            password
        }
    }

    private fun generatePassword(): ByteArray {
        val random = SecureRandom.getInstanceStrong()
        val result = ByteArray(32)

        random.nextBytes(result)

        // filter out zero byte values, as SQLCipher does not like them
        while (result.contains(0)) {
            random.nextBytes(result)
        }

        return result
    }
}