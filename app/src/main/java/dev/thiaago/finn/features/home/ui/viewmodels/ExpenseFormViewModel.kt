package dev.thiaago.finn.features.home.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.thiaago.finn.core.ui.state.FieldState
import dev.thiaago.finn.core.validators.IsNotEmptyValidator
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseType
import dev.thiaago.finn.features.home.domain.entities.RepeatReleaseMode
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ExpenseFormViewModel @Inject constructor() : ViewModel() {

    var valueMoney by mutableStateOf("")
    val descriptionState = FieldState(
        field = "",
        validators = listOf(IsNotEmptyValidator("Insira a descrição do lançamento"))
    )
    var accountState by mutableStateOf<AccountEntity?>(null)
    var dateState by mutableStateOf(Date())
    var releaseTypeState by mutableStateOf(RepeatReleaseMode.NO_REPEAT)

    var showInstallmentsBottomSheet by mutableStateOf(false)
    var installments: Int? by mutableStateOf(null)

    fun getReleaseEntity(releaseType: ReleaseType): ReleaseEntity = ReleaseEntity(
        valueMoney = valueMoney.toInt(),
        description = descriptionState.field.value,
        account = accountState,
        date = Timestamp(date = dateState),
        repeatMode = releaseTypeState,
        releaseType = releaseType,
        installments = installments,
    )

    private fun valueMoneyIsValid(): Boolean = (valueMoney.toFloatOrNull() ?: 0f) > 0

    fun validate(): String? {
        if (!valueMoneyIsValid()) {
            return "Insira o valor do lançamento"
        }

        if (!descriptionState.validate()) {
            return "Insira a descrição do lançamento"
        }

        if (accountState == null) {
            return "Insira a conta do lançamento"
        }

        return null
    }

    fun clearForm() {
        valueMoney = ""
        val descriptionState = FieldState(
            field = "",
            validators = listOf(IsNotEmptyValidator("Insira a descrição do lançamento"))
        )
        accountState = null
        dateState = Date()
        releaseTypeState = RepeatReleaseMode.NO_REPEAT

        showInstallmentsBottomSheet = false
        installments = null
    }
}