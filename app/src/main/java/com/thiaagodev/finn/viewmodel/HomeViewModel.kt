package com.thiaagodev.finn.viewmodel

import androidx.lifecycle.*
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.service.model.Resource
import com.thiaagodev.finn.service.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val accountRepository: AccountRepository) :
    ViewModel() {

    private val _saveAccount = MutableLiveData<Resource<Account>>()
    val saveAccount: LiveData<Resource<Account>> = _saveAccount

    val allAccounts = MediatorLiveData<Resource<List<Account?>>>()

    init {
        allAccounts.addSource(accountRepository.getAll()) {
            allAccounts.value = it
        }
    }

    fun getAllAccounts() {
        accountRepository.getAll()
    }


    fun saveAccount(account: Account) {
        viewModelScope.launch {
            if (account.id == 0.toLong()) {
                _saveAccount.value = accountRepository.insert(account)
            } else {
                _saveAccount.value = accountRepository.update(account)
            }
        }
    }

    fun deleteAccount(account: Account) {
        viewModelScope.launch {
            accountRepository.delete(account)
        }
    }

}