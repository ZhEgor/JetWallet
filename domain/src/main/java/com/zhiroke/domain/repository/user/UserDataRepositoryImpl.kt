package com.zhiroke.domain.repository.user

import com.zhiroke.data.datasources.room.dao.BankCardDao
import com.zhiroke.data.datasources.securedprefs.SecuredPrefs

class UserDataRepositoryImpl(
    private val bankCardDao: BankCardDao,
    private val securedPrefs: SecuredPrefs
) : UserDataRepository {

    override suspend fun setUnlockTime(timeInMillis: Long) {
        securedPrefs.setUnlockTime(timeInMillis = timeInMillis)
    }

    override suspend fun getUnlockTime(): Long? {
        return securedPrefs.getUnlockTime()
    }

    override suspend fun setPIN(pin: String) {
        securedPrefs.setPIN(pin = pin)
    }

    override suspend fun getPIN(): String? {
        return securedPrefs.getPIN()
    }

    override suspend fun dropData() {
        bankCardDao.dropTable()
        securedPrefs.dropData()
    }
}