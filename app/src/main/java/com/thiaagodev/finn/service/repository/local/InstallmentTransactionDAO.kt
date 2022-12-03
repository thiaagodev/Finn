package com.thiaagodev.finn.service.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.thiaagodev.finn.service.model.InstallmentTransaction

@Dao
interface InstallmentTransactionDAO {

    @Insert
    suspend fun insert(installmentTransaction: InstallmentTransaction): Long

    @Update
    suspend fun update(installmentTransaction: InstallmentTransaction): Int

    @Delete
    suspend fun delete(installmentTransaction: InstallmentTransaction)

    @Query("SELECT * FROM InstallmentTransaction")
    fun getAll(): LiveData<List<InstallmentTransaction?>>

    @Query("SELECT * FROM InstallmentTransaction WHERE id = :id")
    fun get(id: Int): LiveData<InstallmentTransaction?>
}