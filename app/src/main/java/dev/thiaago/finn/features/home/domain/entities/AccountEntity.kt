package dev.thiaago.finn.features.home.domain.entities

data class AccountEntity(
    val name: String,
    val balance: Int = 0,
)