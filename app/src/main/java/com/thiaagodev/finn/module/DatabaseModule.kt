package com.thiaagodev.finn.module

import android.content.Context
import androidx.room.Room
import com.thiaagodev.finn.service.repository.local.FinnDatabase
import com.thiaagodev.finn.service.repository.local.AccountDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideAccountDAO(appDatabase: FinnDatabase): AccountDAO {
        return appDatabase.accountDAO()
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): FinnDatabase {
        return Room.databaseBuilder(context, FinnDatabase::class.java, "finnDB")
            .build()
    }

}