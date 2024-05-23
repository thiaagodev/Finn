package dev.thiaago.finn.features.home.domain.entities

enum class ReleaseType {
    EXPENSE,
    REVENUE;

    fun translatedType(): String = when (this) {
        EXPENSE -> "Despesa"
        REVENUE -> "Receita"
    }

}