package dev.thiaago.finn.core.extensions

import java.time.LocalDateTime

fun LocalDateTime.isDay(): Boolean {
    return this.hour < 18
}