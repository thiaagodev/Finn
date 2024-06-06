package dev.thiaago.finn.features.home.data.repositories

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dev.thiaago.finn.core.data.FirebaseCollections
import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity
import dev.thiaago.finn.features.home.domain.errors.ReleaseCreateException
import dev.thiaago.finn.features.home.domain.repositories.ReleaseRepository
import kotlinx.coroutines.tasks.await

class ReleaseRepositoryImpl: ReleaseRepository {
    private val db: FirebaseFirestore = Firebase.firestore

    override suspend fun createRelease(release: ReleaseEntity): Result<Boolean> {
        return try {
            db.collection(FirebaseCollections.RELEASE)
                .add(release)
                .await()

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(ReleaseCreateException())
        }
    }
}