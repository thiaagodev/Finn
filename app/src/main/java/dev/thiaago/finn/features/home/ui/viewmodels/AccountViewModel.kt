package dev.thiaago.finn.features.home.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.usecases.CreateAccountUseCase
import dev.thiaago.finn.features.home.domain.usecases.GetAccountListUseCase
import dev.thiaago.finn.features.home.ui.states.AccountState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    private val getAccountListUseCase: GetAccountListUseCase,
) : ViewModel() {
    private val _accountListState = MutableStateFlow<AccountState>(AccountState.AccountInitial)
    val accountListState = _accountListState.asStateFlow()

    init {
        getAccountList()
    }

    private fun getAccountList() {
        viewModelScope.launch {
            _accountListState.value = AccountState.GetListAccountLoading
            val accountList = getAccountListUseCase()

            accountList.fold(
                onSuccess =
                {
                    _accountListState.value = AccountState.GetListAccountSuccess(it)
                },
                onFailure = {
                    _accountListState.value = AccountState.GetListAccountError
                }
            )
        }
    }

    fun createAccount(accountEntity: AccountEntity) {
        viewModelScope.launch {
            createAccountUseCase(accountEntity)

            getAccountList()
        }
    }
}