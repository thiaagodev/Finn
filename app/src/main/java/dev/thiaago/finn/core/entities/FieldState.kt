package dev.thiaago.finn.core.entities
import kotlinx.coroutines.flow.MutableStateFlow

class FieldState(
    field: String,
    private val validators: List<Validator>
) {
    var field = MutableStateFlow(field)

    var error = MutableStateFlow<String?>(null)
        private set

    fun validate(): Boolean {
        val errors = validators.map {
            it.validate(field.value)
        }.toMutableList()

        errors.removeAll { it.isNullOrEmpty() }

        if(errors.isEmpty()) {
            error.value = null
        } else {
            errors.forEach {
                error.value = "${it}\n"
            }
        }

        return error.value?.isEmpty() ?: true
    }
}