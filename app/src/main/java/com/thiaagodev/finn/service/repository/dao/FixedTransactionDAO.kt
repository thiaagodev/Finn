package com.thiaagodev.finn.service.repository.dao

import androidx.room.*
import com.thiaagodev.finn.service.model.FixedTransaction

@Dao
interface FixedTransactionDAO {

    @Insert
    suspend fun insert(fixedTransaction: FixedTransaction): Long

    @Update
    suspend fun update(fixedTransaction: FixedTransaction): Int

    @Delete
    suspend fun delete(fixedTransaction: FixedTransaction)

    @Query("SELECT * FROM FixedTransaction")
    suspend fun getAll(): List<FixedTransaction?>

    @Query("SELECT * FROM FixedTransaction WHERE id = :id")
    suspend fun get(id: Int): FixedTransaction?
}