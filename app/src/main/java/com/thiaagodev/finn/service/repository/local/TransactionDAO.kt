package com.thiaagodev.finn.service.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.thiaagodev.finn.service.model.Transaction

@Dao
interface TransactionDAO {

    @Insert
    suspend fun insert(transaction: Transaction): Long

    @Update
    suspend fun update(transaction: Transaction): Int

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("SELECT * FROM `Transaction`")
    fun getAll(): LiveData<List<Transaction?>>

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    fun get(id: Int): LiveData<Transaction?>
}