package dev.thiaago.finn.core.entities

interface Validator {
    fun validate(value: String): String?
}