package com.thiaagodev.finn.service.model

import androidx.room.*
import com.thiaagodev.finn.service.converter.DateConverter
import com.thiaagodev.finn.service.enum.TransactionType
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(
    tableName = "Transaction",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("account")
        ),
        ForeignKey(
            entity = FixedTransaction::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("fixed")
        ),
        ForeignKey(
            entity = InstallmentTransaction::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("installment")
        )
    ],
    indices = [
        Index(value = arrayOf("account"), unique = false),
        Index(value = arrayOf("fixed"), unique = false),
        Index(value = arrayOf("installment"), unique = false)
    ]
)
class Transaction {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long = 0

    @ColumnInfo
    var description: String = ""

    @ColumnInfo
    var account: Int? = null

    @TypeConverters(DateConverter::class)
    @ColumnInfo
    var date: Date = Date(System.currentTimeMillis())

    @ColumnInfo
    var fixed: Int? = null

    @ColumnInfo
    var installment: Int? = null

    @ColumnInfo
    var value: Double = 0.0

    @ColumnInfo
    var type: String = TransactionType.REVENUE.toString()


    @ColumnInfo
    var paid: Boolean = false

}