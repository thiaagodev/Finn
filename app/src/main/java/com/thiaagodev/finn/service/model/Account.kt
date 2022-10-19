package com.thiaagodev.finn.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")
class Account {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long = 0

    @ColumnInfo()
    var name: String = ""

    @ColumnInfo()
    var balance: Double = 0.0

}