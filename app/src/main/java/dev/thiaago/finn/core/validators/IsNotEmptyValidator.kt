package dev.thiaago.finn.core.validators

import dev.thiaago.finn.core.entities.Validator

class IsNotEmptyValidator(private val error: String) : Validator {
    override fun validate(value: String): String? {
        if (value.isEmpty()) {
            return error
        }

        return null
    }
}