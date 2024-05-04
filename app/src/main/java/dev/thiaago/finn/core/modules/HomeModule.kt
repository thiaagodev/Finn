package dev.thiaago.finn.core.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.thiaago.finn.features.home.data.repositories.AccountRepositoryImpl
import dev.thiaago.finn.features.home.domain.repositories.AccountRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository
}