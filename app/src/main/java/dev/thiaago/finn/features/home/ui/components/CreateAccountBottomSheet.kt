package dev.thiaago.finn.features.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.thiaago.finn.core.ui.state.FieldState
import dev.thiaago.finn.core.ui.components.BottomSheetHeader
import dev.thiaago.finn.core.ui.components.CustomOutlinedTextField
import dev.thiaago.finn.core.ui.components.SimpleButton
import dev.thiaago.finn.core.validators.IsNotEmptyValidator

@Composable
fun CreateAccountBottomSheet(
    onClose: () -> Unit,
    onConfirm: (accountName: String) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        BottomSheetHeader(
            title = "Cadastrar Conta",
            onClose = onClose
        )
        Spacer(modifier = Modifier.height(32.dp))

        val accountNameState = FieldState(
            field = "",
            validators = listOf(IsNotEmptyValidator("Insira o nome da sua conta"))
        )

        CustomOutlinedTextField(
            label = "Cadastrar Conta",
            placeholder = "Insira o nome da conta",
            fieldState = accountNameState,
            validateOnChange = true
        )

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .padding(48.dp)
        ) {
            SimpleButton(
                Modifier.align(Alignment.BottomCenter),
                text = "Confirmar"
            ) {
                if (accountNameState.validate()) {
                    onConfirm(accountNameState.field.value)
                    onClose()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateAccountBottomSheetPreview() {
    CreateAccountBottomSheet(onClose = {}, onConfirm = {})
}