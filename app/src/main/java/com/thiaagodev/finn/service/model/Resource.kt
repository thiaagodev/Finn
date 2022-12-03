package com.thiaagodev.finn.service.model

data class Resource<T>(
    val data: T?,
    val error: String? = null
) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): Resource<T>? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            this
        }
    }
}