package dev.thiaago.finn.features.home.domain.repositories

import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity

interface ReleaseRepository {
    suspend fun createRelease(release: ReleaseEntity): Result<Boolean>
}