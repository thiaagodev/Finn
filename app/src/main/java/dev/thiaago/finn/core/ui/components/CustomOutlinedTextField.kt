package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.entities.FieldState
import dev.thiaago.finn.core.ui.theme.FinnColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    label: String,
    material3Label: Boolean = false,
    placeholder: String,
    fieldState: FieldState,
    validateOnChange: Boolean = false,
    showClearIcon: Boolean = false,
) {
    val field = fieldState.field
    val error = fieldState.error

    Column(modifier = modifier) {
        if (!material3Label) {
            Text(
                text = label,
                style = TextStyle(
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            modifier = Modifier
                .wrapContentWidth()
                .defaultMinSize(minWidth = 250.dp),
            value = field.value,
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 14.sp,
            ),
            onValueChange = {
                fieldState.field.value = it
                if (validateOnChange) {
                    fieldState.validate()
                }
            },
        ) {
            OutlinedTextFieldDefaults.DecorationBox(
                value = field.value,
                innerTextField = it,
                enabled = true,
                singleLine = true,
                placeholder = {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.Gray
                        ),
                    )
                },
                label = {
                    if (material3Label) {
                        Text(
                            text = label,
                            style = TextStyle(
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        )
                    }
                },
                contentPadding = PaddingValues(12.dp),
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    if (showClearIcon && field.value.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable {
                                fieldState.field.value = ""
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Limpar"
                        )
                    }
                },
                interactionSource = remember {
                    MutableInteractionSource()
                }
            )
        }

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CustomOutlinedTextFieldPreview() {
    CustomOutlinedTextField(
        label = "Teste",
        placeholder = "Teste",
        fieldState = FieldState("Teste")
    )

}