package dev.thiaago.finn.features.home.domain.entities

data class AccountEntity(
    val id: String? = null,
    val ownerId: String? = null,
    val name: String,
    var balance: Long = 0,
) {
    companion object {
        const val ID = "id"
        const val OWNER_ID = "ownerId"
        const val NAME = "name"
        const val BALANCE = "balance"

        fun fromMap(map: Map<String, Any>) : AccountEntity {
            return AccountEntity(
                id = map[ID] as String?,
                ownerId = map[OWNER_ID] as String?,
                name = map[NAME] as String? ?: "",
                balance = map[BALANCE] as Long? ?: 0,
            )
        }
    }
}
