package com.thiaagodev.finn.service.repository.dao

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
    suspend fun getAll(): List<Transaction?>

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    suspend fun get(id: Int): Transaction?
}