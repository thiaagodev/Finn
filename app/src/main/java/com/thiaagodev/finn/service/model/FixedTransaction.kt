package com.thiaagodev.finn.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FixedTransaction")
class FixedTransaction {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long = 0

    @ColumnInfo
    var type: String = ""
}