package dev.thiaago.finn.features.home.domain.entities

import com.google.firebase.Timestamp

data class ReleaseEntity(
    val id: String? = null,
    val valueMoney: Int,
    val account: AccountEntity,
    val releaseType: ReleaseType,
    val installments: Int?,
    val repeatMode: RepeatReleaseMode,
    val description: String,
    val date: Timestamp,
    val deletedAt: Timestamp?,
    val payments: List<ReleasePayment>? = listOf(),
)
