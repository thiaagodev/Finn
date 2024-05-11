package dev.thiaago.finn.core.validators

interface Validator {
    fun validate(value: String): String?
}