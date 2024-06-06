package dev.thiaago.finn.features.home.domain.repositories

import dev.thiaago.finn.features.home.domain.entities.AccountEntity

interface AccountRepository {
    suspend fun createAccount(accountEntity: AccountEntity): Result<Boolean>
    suspend fun getAccountList(): Result<List<AccountEntity>>
    suspend fun updateAccountBalance(accountEntity: AccountEntity, balance: Int): Result<Boolean>
}