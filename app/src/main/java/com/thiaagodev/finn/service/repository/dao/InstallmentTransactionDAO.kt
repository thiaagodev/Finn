package com.thiaagodev.finn.service.repository.dao

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
    suspend fun getAll(): List<InstallmentTransaction?>

    @Query("SELECT * FROM InstallmentTransaction WHERE id = :id")
    suspend fun get(id: Int): InstallmentTransaction?
}