package dev.thiaago.finn.features.home.domain.usecases

import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity
import dev.thiaago.finn.features.home.domain.repositories.ReleaseRepository
import javax.inject.Inject

class CreateReleaseUseCase @Inject constructor(
    private val repository: ReleaseRepository
) {
    suspend operator fun invoke(release: ReleaseEntity): Result<Boolean> {
        return repository.createRelease(release)
    }
}