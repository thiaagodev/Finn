package com.thiaagodev.finn.service.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.service.model.FixedTransaction
import com.thiaagodev.finn.service.model.InstallmentTransaction
import com.thiaagodev.finn.service.model.Transaction

@Database(
    entities = [
        Account::class,
        FixedTransaction::class,
        InstallmentTransaction::class,
        Transaction::class
    ], version = 1, exportSchema = false
)
abstract class FinnDatabase : RoomDatabase() {

    abstract fun accountDAO(): AccountDAO
    abstract fun fixedTransactionDAO(): FixedTransactionDAO
    abstract fun installmentTransactionDAO(): InstallmentTransactionDAO
    abstract fun transactionDAO(): TransactionDAO
}