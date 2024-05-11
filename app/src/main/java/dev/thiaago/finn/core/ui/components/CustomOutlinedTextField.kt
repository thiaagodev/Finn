package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.entities.FieldState
import dev.thiaago.finn.core.ui.theme.FinnColors

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    fieldState: FieldState,
    validateOnChange: Boolean = false,
) {
    val field = fieldState.field.collectAsState()
    val error = fieldState.error.collectAsState()

    Column(modifier = modifier) {
        Text(
            text = label,
            style = TextStyle(
                color = Color.Gray,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = field.value,
            placeholder = {
                Text(
                    text = placeholder,
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
                fieldState.field.value = it
                if(validateOnChange) {
                    fieldState.validate()
                }
            },
            isError = error.value?.isEmpty() == false,
        )

        if (error.value?.isEmpty() == false) {
            Text(
                text = error.value ?: "",
                style = TextStyle(
                    color = FinnColors.errorColor
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}