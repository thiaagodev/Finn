package com.thiaagodev.finn.service.repository.local

import androidx.lifecycle.LiveData
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
    fun getAll(): LiveData<List<Account?>>

    @Query("SELECT * FROM Account WHERE id = :id")
    fun get(id: Int): LiveData<Account?>
}