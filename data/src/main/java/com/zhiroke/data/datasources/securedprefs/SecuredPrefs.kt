package com.zhiroke.data.datasources.securedprefs

import android.content.SharedPreferences
import com.zhiroke.data.util.drop
import com.zhiroke.data.util.get
import com.zhiroke.data.util.set

interface SecuredPrefs {

    suspend fun setUnlockTime(timeInMillis: Long)

    suspend fun getUnlockTime(): Long?

    suspend fun setPIN(pin: String)

    suspend fun getPIN(): String?

    suspend fun dropData()
}

class SecuredPrefsImpl(
    private val prefs: SharedPreferences
) : SecuredPrefs {

    override suspend fun setUnlockTime(timeInMillis: Long) {
        prefs[UNLOCK_TIME] = timeInMillis
    }

    override suspend fun getUnlockTime(): Long? {
        return prefs[UNLOCK_TIME]
    }

    override suspend fun setPIN(pin: String) {
        prefs[PIN] = pin
    }

    override suspend fun getPIN(): String? {
        return prefs[PIN]
    }

    override suspend fun dropData() {
        prefs.drop()
    }

    companion object {
        const val UNLOCK_TIME = "unlock_time"
        const val PIN = "pin"
    }
}

