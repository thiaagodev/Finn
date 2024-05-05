package dev.thiaago.finn.features.home.data.repositories

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dev.thiaago.finn.core.data.FirebaseCollections
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.errors.AccountCreateException
import dev.thiaago.finn.features.home.domain.errors.GetAccountListException
import dev.thiaago.finn.features.home.domain.repositories.AccountRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor() : AccountRepository {
    private val db: FirebaseFirestore = Firebase.firestore

    override suspend fun createAccount(accountEntity: AccountEntity): Result<Boolean> {
        return try {
            db.collection(FirebaseCollections.ACCOUNT)
                .add(accountEntity)
                .await()

            Result.success(true)
        } catch (e: Exception) {
            Result.failure(AccountCreateException())
        }
    }

    override suspend fun getAccountList(): Result<List<AccountEntity>> {
        return try {
            val accounts = db.collection(FirebaseCollections.ACCOUNT)
                .get()
                .await()
                .documents

            val accountEntityList = accounts.map {
                AccountEntity.fromMap(it.data ?: mapOf())
            }.toList()

            Result.success(accountEntityList)
        } catch (e: Exception) {
            Result.failure(GetAccountListException())
        }

    }
}