package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.ui.theme.FinnColors

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    placeholder: String,
    error: String?,
    onValueChanged: (value: String) -> Unit
) {

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
            value = value,
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
            onValueChange = onValueChanged,
            isError = error?.isEmpty() == false,
        )

        if (error?.isEmpty() == false) {
            Text(
                text = error,
                style = TextStyle(
                    color = FinnColors.errorColor
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}