package dev.thiaago.finn.features.home.domain.usecases

import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.repositories.AccountRepository
import javax.inject.Inject

class GetAccountListUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(): Result<List<AccountEntity>> {
        return repository.getAccountList()
    }
}