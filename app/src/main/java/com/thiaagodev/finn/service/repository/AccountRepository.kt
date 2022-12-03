package com.thiaagodev.finn.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.service.model.Resource
import com.thiaagodev.finn.service.repository.local.AccountDAO
import javax.inject.Inject

class AccountRepository @Inject constructor(private val database: AccountDAO) {

    private val accountsMediator = MediatorLiveData<Resource<List<Account?>>>()

    suspend fun insert(account: Account): Resource<Account> {
        database.insert(account)

        return Resource(null, null)
    }

    fun getAll(): LiveData<Resource<List<Account?>>> {
        accountsMediator.addSource(database.getAll()) {
            accountsMediator.value = Resource(it)
        }

        return accountsMediator
    }

    suspend fun delete(account: Account) = database.delete(account)

    suspend fun update(account: Account): Resource<Account> {
        database.update(account)

        return Resource(null, null)
    }

}