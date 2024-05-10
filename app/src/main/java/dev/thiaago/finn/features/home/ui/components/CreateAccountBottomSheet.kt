package dev.thiaago.finn.features.home.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountBottomSheet(onClose: () -> Unit) {
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

        OutlinedTextField(
            modifier = Modifier.padding(top = 8.dp),
            value = "",
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
            onValueChange = {}
        )

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            SimpleButton(
                Modifier.align(Alignment.BottomCenter),
                text = "Confirmar"
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateAccountBottomSheetPreview() {
    CreateAccountBottomSheet(onClose = {})
}