package dev.thiaago.finn.core.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.toBrazilianCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
}