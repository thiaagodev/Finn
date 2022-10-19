package com.thiaagodev.finn.service.repository.dao

import androidx.room.*
import com.thiaagodev.finn.service.model.Account

@Dao
interface AccountDAO {

    @Insert
    suspend fun insert(account: Account): Long

    @Update
    suspend fun update(account: Account): Int

    @Delete
    suspend fun delete(account: Account)

    @Query("SELECT * FROM Account")
    suspend fun getAll(): List<Account?>

    @Query("SELECT * FROM Account WHERE id = :id")
    suspend fun get(id: Int): Account?
}