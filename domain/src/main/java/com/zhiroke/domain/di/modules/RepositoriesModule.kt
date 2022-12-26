package com.zhiroke.domain.di.modules

import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepository
import com.zhiroke.domain.repository.wallet.bankcard.BankCardRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


fun repositoriesModule() = module {

    singleOf(::BankCardRepositoryImpl) { bind<BankCardRepository>() }

}