package com.zhiroke.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.zhiroke.data.base.BaseDao
import com.zhiroke.data.models.BankCardLocal
import kotlinx.coroutines.flow.Flow


@Dao
interface BankCardDao : BaseDao<BankCardLocal> {

    @Query("DELETE FROM ${BankCardLocal.TABLE_NAME} WHERE ${BankCardLocal.COLUMN_ID} = :id")
    fun deleteBankCardById(id: String)

    @Query("SELECT * FROM ${BankCardLocal.TABLE_NAME}")
    fun readAll(): Flow<List<BankCardLocal>>
}