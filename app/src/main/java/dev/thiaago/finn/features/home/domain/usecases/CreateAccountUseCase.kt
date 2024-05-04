package dev.thiaago.finn.features.home.domain.usecases

import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.repositories.AccountRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    suspend operator fun invoke(accountEntity: AccountEntity): Result<Boolean> {
        return repository.createAccount(accountEntity)
    }
}