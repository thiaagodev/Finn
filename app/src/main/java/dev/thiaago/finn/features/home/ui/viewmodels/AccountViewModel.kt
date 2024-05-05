package dev.thiaago.finn.features.home.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.usecases.CreateAccountUseCase
import dev.thiaago.finn.features.home.domain.usecases.GetAccountListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val createAccountUseCase: CreateAccountUseCase,
    private val getAccountListUseCase: GetAccountListUseCase,
) : ViewModel() {

}