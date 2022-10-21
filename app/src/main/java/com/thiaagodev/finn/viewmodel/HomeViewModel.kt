package com.thiaagodev.finn.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.thiaagodev.finn.R
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.service.repository.AccountRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val accountRepository = AccountRepository(application)
    private val allAccounts = MutableLiveData<List<Account?>>()
    private val _saveAccount = MutableLiveData<Event<String>>()

    val accounts: LiveData<List<Account?>> = allAccounts
    val saveAccount: LiveData<Event<String>> = _saveAccount

    fun getAllAccounts() {
        viewModelScope.launch {
            allAccounts.value = accountRepository.getAll()
        }
    }


    fun saveAccount(account: Account) {
        viewModelScope.launch {
            if(account.id == 0.toLong()) {
                if (accountRepository.insert(account)) {
                    _saveAccount.value = Event(context.getString(R.string.sucess_insert_account))
                } else {
                    _saveAccount.value = Event(context.getString(R.string.failure_insert_account))
                }
            } else {
               if(accountRepository.update(account)) {
                   _saveAccount.value = Event(context.getString(R.string.sucess_update_account))
               }  else {
                   _saveAccount.value = Event(context.getString(R.string.failure_update_account))
               }
            }

            getAllAccounts()
        }
    }

    fun deleteAccount(account: Account) {
        viewModelScope.launch {
            accountRepository.delete(account)
            getAllAccounts()
        }
    }

}