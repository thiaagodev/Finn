package dev.thiaago.finn.features.home.domain.entities

import com.google.firebase.Timestamp

@Suppress("UNCHECKED_CAST")
data class ReleaseEntity(
    val id: String? = null,
    val valueMoney: Int,
    val account: AccountEntity?,
    val releaseType: ReleaseType,
    val installments: Int? = null,
    val repeatMode: RepeatReleaseMode,
    val description: String,
    val date: Timestamp?,
    val deletedAt: Timestamp? = null,
    val payments: List<ReleasePayment>? = listOf(),
) {
    companion object {
        const val ID = "id"
        const val VALUE_MONEY = "valueMoney"
        const val ACCOUNT = "account"
        const val RELEASE_TYPE = "releaseType"
        const val INSTALLMENTS = "installments"
        const val REPEAT_MODE = "repeatMode"
        const val DESCRIPTION = "description"
        const val DATE = "date"
        const val DELETED_AT = "deletedAt"
        const val PAYMENTS = "payments"

        fun fromMap(map: Map<String, Any>): ReleaseEntity {
            return ReleaseEntity(
                id = map[ID] as String?,
                valueMoney = map[VALUE_MONEY] as Int,
                account = map[ACCOUNT] as AccountEntity?,
                date = map[DATE] as Timestamp,
                releaseType = ReleaseType.valueOf(map[RELEASE_TYPE] as String),
                repeatMode = RepeatReleaseMode.valueOf(map[REPEAT_MODE] as String),
                description = map[DESCRIPTION] as String,
                installments = map[INSTALLMENTS] as Int?,
                payments = map[PAYMENTS] as List<ReleasePayment>? ?: listOf(),
                deletedAt = map[DELETED_AT] as Timestamp?,
            )
        }
    }
}
