package dev.thiaago.finn.core.entities
import kotlinx.coroutines.flow.MutableStateFlow

class InputValue<T>(
    field: T,
    val onValidate: (value: T) -> String? = { null }
) {
    var field = MutableStateFlow(field)
    var error = MutableStateFlow<String?>(null)
        private set

    fun validate(): Boolean {
        error.value = onValidate(field.value)

        return error.value?.isEmpty() ?: true
    }
}