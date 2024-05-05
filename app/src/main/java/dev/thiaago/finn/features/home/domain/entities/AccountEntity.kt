package dev.thiaago.finn.features.home.domain.entities

data class AccountEntity(
    val id: String? = null,
    val name: String,
    val balance: Int = 0,
) {
    companion object {
        fun fromMap(map: Map<String, Any>) : AccountEntity {
            return AccountEntity(
                id = map["id"] as String?,
                name = map["name"] as String? ?: "",
                balance = map["balance"] as Int? ?: 0,
            )
        }
    }
}
