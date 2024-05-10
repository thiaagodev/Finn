package dev.thiaago.finn.features.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.ui.components.BottomSheetHeader
import dev.thiaago.finn.core.ui.components.SimpleButton
import dev.thiaago.finn.core.ui.theme.FinnColors

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

        Text(
            text = "Nome da conta",
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )

        var accountName by remember {
            mutableStateOf("")
        }

        var isError by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            modifier = Modifier.padding(top = 8.dp),
            value = accountName,
            placeholder = {
                Text(
                    text = "Insira o nome da conta",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Gray
                    ),
                )
            },
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            shape = RoundedCornerShape(16.dp),
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                accountName = it
                isError = accountName.isEmpty()
            },
            isError = isError,
        )

        if (isError) {
            Text(
                text = "Nome da conta deve ser válido",
                style = TextStyle(
                    color = FinnColors.errorColor
                ),
                modifier = Modifier.padding(8.dp)
            )
        }

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
                if (accountName.isEmpty()) {
                    isError = true
                } else {
                    onConfirm(accountName)
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