package dev.thiaago.finn.features.home.ui.states

import dev.thiaago.finn.features.home.domain.entities.AccountEntity

sealed class AccountState {
    data object AccountInitial : AccountState()
    data class GetListAccountSuccess(val accounts: List<AccountEntity>): AccountState()
    data object GetListAccountLoading : AccountState()
    data object GetListAccountError : AccountState()
}