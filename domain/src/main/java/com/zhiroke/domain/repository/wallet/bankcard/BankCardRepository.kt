package com.zhiroke.domain.repository.wallet.bankcard

import com.zhiroke.domain.models.BankCard
import kotlinx.coroutines.flow.Flow

interface BankCardRepository {

    suspend fun addBankCard(bankCard: BankCard)

    suspend fun updateBankCard(bankCard: BankCard)

    suspend fun removeBankCard(bankCard: BankCard)

    fun fetchAll(): Flow<List<BankCard>>
}
