package dev.thiaago.finn.core.ui.state
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.thiaago.finn.core.validators.Validator

class FieldState(
    field: String,
    private val validators: List<Validator> = listOf(),
) {
    var field = mutableStateOf(field)

    var error: MutableState<String?> = mutableStateOf(null)
        private set

    fun validate(): Boolean {
        val errors = validators.map {
            it.validate(field.value)
        }.toMutableList()

        errors.removeAll { it.isNullOrEmpty() }

        if(errors.isEmpty()) {
            error.value = null
        } else {
            error.value = ""
            errors.forEach {
                error.value += "${it}\n"
            }
        }

        return error.value?.isEmpty() ?: true
    }
}