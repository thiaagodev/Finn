package dev.thiaago.finn.features.home.domain.entities

enum class ReleaseType {
    EXPENSE,
    INCOME;

    fun translatedType(): String = when (this) {
        EXPENSE -> "Despesa"
        INCOME -> "Receita"
    }

}