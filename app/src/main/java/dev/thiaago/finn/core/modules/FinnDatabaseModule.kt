package dev.thiaago.finn.core.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.thiaago.finn.core.db.FinnDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FinnDatabaseModule {

    @Singleton
    @Provides
    fun provideFinnDatabase(@ApplicationContext context: Context): FinnDatabase {
        return Room.databaseBuilder(context, FinnDatabase::class.java, "finn-database").build()
    }
}