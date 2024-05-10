package dev.thiaago.finn.core.extensions

fun String.validateIsNotEmpty(error: String): String? {
    if (isEmpty()) {
        return error
    }

    return null
}
