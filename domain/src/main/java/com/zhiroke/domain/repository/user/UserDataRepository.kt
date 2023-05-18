package com.zhiroke.domain.repository.user

interface UserDataRepository {

    suspend fun setUnlockTime(timeInMillis: Long)

    suspend fun getUnlockTime(): Long?

    suspend fun setPIN(pin: String)

    suspend fun getPIN(): String?

    suspend fun dropData()
}

