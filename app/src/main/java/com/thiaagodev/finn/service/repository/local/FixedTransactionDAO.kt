package com.thiaagodev.finn.service.repository.local

import androidx.lifecycle.LiveData
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
    fun getAll(): LiveData<List<FixedTransaction?>>

    @Query("SELECT * FROM FixedTransaction WHERE id = :id")
    fun get(id: Int): LiveData<FixedTransaction?>
}