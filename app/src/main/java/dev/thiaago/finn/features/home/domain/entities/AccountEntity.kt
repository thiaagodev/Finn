package dev.thiaago.finn.features.home.domain.entities

data class AccountEntity(
    val id: String? = null,
    val ownerId: String? = null,
    val name: String,
    val balance: Long = 0,
) {
    companion object {
        fun fromMap(map: Map<String, Any>) : AccountEntity {
            return AccountEntity(
                id = map["id"] as String?,
                ownerId = map["ownerId"] as String?,
                name = map["name"] as String? ?: "",
                balance = map["balance"] as Long? ?: 0,
            )
        }
    }
}
