package dev.thiaago.finn.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.thiaago.finn.features.home.data.local.AccountEntity

@Database(entities = [AccountEntity::class], version = 1)
abstract class FinnDatabase : RoomDatabase() {

}