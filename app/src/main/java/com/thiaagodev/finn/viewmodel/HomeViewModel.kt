package com.thiaagodev.finn.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.service.repository.AccountRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val accountRepository = AccountRepository(application)
    private val allAccounts = MutableLiveData<List<Account?>>()

    val accounts: LiveData<List<Account?>> = allAccounts

    fun getAllAccounts() {
        viewModelScope.launch {
            allAccounts.value = accountRepository.getAll()
        }
    }


    fun saveAccount(account: Account) {
        viewModelScope.launch {
            accountRepository.insert(account)
        }
    }

}