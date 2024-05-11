package dev.thiaago.finn.core.validators

class IsNotEmptyValidator(private val error: String) : Validator {
    override fun validate(value: String): String? {
        if (value.trim().isEmpty()) {
            return error
        }

        return null
    }
}