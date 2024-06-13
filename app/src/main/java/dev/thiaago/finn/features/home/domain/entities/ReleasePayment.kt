package dev.thiaago.finn.features.home.domain.entities

import com.google.firebase.Timestamp

data class ReleasePayment(
    val installment: Int? = null,
    val date: Timestamp,
    val payed: Boolean,
)
