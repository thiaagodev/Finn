package com.thiaagodev.finn.service.repository

import android.content.Context
import com.thiaagodev.finn.service.model.Account

class AccountRepository(context: Context) {

    private val database = FinnDatabase.getDatabase(context).accountDAO()


    suspend fun insert(account: Account): Boolean {
        return database.insert(account) > 0
    }

    suspend fun getAll(): List<Account?> {
        return database.getAll()
    }

}