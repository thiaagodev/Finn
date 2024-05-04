package dev.thiaago.finn.core.extensions

fun String.firstWord(): String {
    return this.split(" ").first()
}