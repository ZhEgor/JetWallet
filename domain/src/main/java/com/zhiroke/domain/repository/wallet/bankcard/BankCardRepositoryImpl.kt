package com.zhiroke.domain.repository.wallet.bankcard

import com.zhiroke.data.room.dao.BankCardDao
import com.zhiroke.domain.models.BankCard
import com.zhiroke.domain.models.toLocal
import com.zhiroke.domain.models.toUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

internal class BankCardRepositoryImpl(private val bankCardDao: BankCardDao) : BankCardRepository {

    override suspend fun addBankCard(bankCard: BankCard) {
        bankCardDao.insert(bankCard.toLocal())
    }

    override suspend fun updateBankCard(bankCard: BankCard) {
        bankCardDao.update(bankCard.toLocal())
    }

    override suspend fun deleteBankCard(bankCard: BankCard) {
        bankCardDao.deleteBankCardById(id = bankCard.id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun fetchAll(): Flow<List<BankCard>> {
        return bankCardDao.readAll().flatMapLatest { list -> flowOf(list.map { localCard -> localCard.toUi() }) }
    }
}
