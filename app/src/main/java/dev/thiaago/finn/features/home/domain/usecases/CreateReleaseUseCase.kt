package dev.thiaago.finn.features.home.domain.usecases

import dev.thiaago.finn.features.home.data.repositories.AccountRepositoryImpl
import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseType
import dev.thiaago.finn.features.home.domain.repositories.ReleaseRepository
import javax.inject.Inject

class CreateReleaseUseCase @Inject constructor(
    private val releaseRepository: ReleaseRepository,
    private val accountRepository: AccountRepositoryImpl,
) {
    suspend operator fun invoke(release: ReleaseEntity): Result<Boolean> {
        return run {
            releaseRepository.createRelease(release.apply {
                account?.balance =
                    if (release.releaseType == ReleaseType.EXPENSE)
                        ((account?.balance ?: 0) - release.valueMoney)
                    else ((account?.balance ?: 0) + release.valueMoney)
            })
            release.account?.let {
                val balance = release.account.balance
                accountRepository.updateAccountBalance(it, balance.toInt())
            }

            Result.success(true)
        }
    }
}