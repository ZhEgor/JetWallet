package com.zhiroke.data.datasources.keystore

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.io.InputStream
import java.io.OutputStream
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec


class KeyStoreHelper {

    private val keyStore = KeyStore.getInstance(KEY_STORE_TYPE).apply { load(null) }

    private fun getEncryptionCipher(): Cipher {
        return Cipher.getInstance("$ENCRYPTION_ALGORITHM/$ENCRYPTION_MODE/$ENCRYPTION_PADDING").apply {
            init(
                Cipher.ENCRYPT_MODE,
                keyStore.getKey(
                    alias = ALIAS,
                    algorithm = ENCRYPTION_ALGORITHM,
                    mode = ENCRYPTION_MODE,
                    padding = ENCRYPTION_PADDING
                ),
            )
        }
    }

    private fun getDecryptionCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance("$ENCRYPTION_ALGORITHM/$ENCRYPTION_MODE/$ENCRYPTION_PADDING").apply {
            init(
                Cipher.DECRYPT_MODE,
                keyStore.getKey(
                    alias = ALIAS,
                    algorithm = ENCRYPTION_ALGORITHM,
                    mode = ENCRYPTION_MODE,
                    padding = ENCRYPTION_PADDING
                ),
                IvParameterSpec(iv)
            )
        }
    }

    fun encrypt(outputStream: OutputStream, bytes: ByteArray): ByteArray {
        val encryptionCipher = getEncryptionCipher()
        val encryptedBytes = encryptionCipher.doFinal(bytes)

        outputStream.use { stream ->
            stream.write(encryptionCipher.iv.size)
            stream.write(encryptionCipher.iv)
            stream.write(encryptedBytes.size)
            stream.write(encryptedBytes)
        }
        return encryptedBytes
    }

    fun decrypt(inputStream: InputStream): ByteArray {
        return inputStream.use { stream ->
            val iv = ByteArray(size = stream.read())
            stream.read(iv)

            val encryptedBytes = ByteArray(size = stream.read())
            stream.read(encryptedBytes)

            getDecryptionCipherForIv(iv = iv).doFinal(encryptedBytes)
        }
    }

    private companion object {
        const val ENCRYPTION_ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        const val KEY_STORE_TYPE = "AndroidKeyStore"
        const val ALIAS = "jet_wallet_alias"
        const val ENCRYPTION_PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        const val ENCRYPTION_MODE = KeyProperties.BLOCK_MODE_CBC
    }
}

private fun KeyStore.getKey(
    alias: String,
    algorithm: String,
    mode: String,
    padding: String,
): SecretKey {
    // getExistingKey
    return (getEntry(alias, null) as? KeyStore.SecretKeyEntry)?.secretKey
        ?: createDecryptEncryptKey( // createNewKey
            alias = alias,
            algorithm = algorithm,
            mode = mode,
            padding = padding
        )
}

private fun createDecryptEncryptKey(
    alias: String,
    algorithm: String,
    mode: String,
    padding: String,
): SecretKey {
    return KeyGenerator.getInstance(algorithm).apply {
        init(
            KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setRandomizedEncryptionRequired(true)
                .setUserAuthenticationRequired(false)
                .setEncryptionPaddings(padding)
                .setBlockModes(mode)
                .build()
        )
    }.generateKey()
}