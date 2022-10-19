package com.thiaagodev.finn.service.repository

import android.content.Context
import androidx.room.*
import com.thiaagodev.finn.service.model.Account
import com.thiaagodev.finn.service.model.FixedTransaction
import com.thiaagodev.finn.service.model.InstallmentTransaction
import com.thiaagodev.finn.service.model.Transaction
import com.thiaagodev.finn.service.repository.dao.AccountDAO
import com.thiaagodev.finn.service.repository.dao.FixedTransactionDAO
import com.thiaagodev.finn.service.repository.dao.InstallmentTransactionDAO
import com.thiaagodev.finn.service.repository.dao.TransactionDAO

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

    companion object {
        private lateinit var INSTANCE: FinnDatabase

        fun getDatabase(context: Context): FinnDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(FinnDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, FinnDatabase::class.java, "finnDB")
                        .build()
                }
            }

            return INSTANCE
        }


    }

}