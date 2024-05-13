package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.extensions.toBrazilianDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerInput(onDateSelected: (date: Date) -> Unit) {

    var openDateDialog by remember {
        mutableStateOf(false)
    }
    val showClearIcon by remember {
        mutableStateOf(false)
    }
    var date by remember {
        mutableStateOf(Date().time.toBrazilianDateFormat())
    }

    BasicTextField(
        modifier = Modifier
            .wrapContentWidth()
            .defaultMinSize(minWidth = 250.dp)
            .clickable { openDateDialog = true },
        value = date,
        onValueChange = {},
        maxLines = 1,
        singleLine = true,
        enabled = false
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = date,
            innerTextField = it,
            enabled = true,
            singleLine = true,
            placeholder = {
                Text(
                    text = "Data",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray
                    ),
                )
            },
            contentPadding = PaddingValues(12.dp),
            visualTransformation = VisualTransformation.None,
            trailingIcon = {
                if (showClearIcon && date.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            date = ""
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

    val dateState = rememberDatePickerState()

    if (openDateDialog) {
        DatePickerDialog(
            onDismissRequest = {
                openDateDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        dateState.selectedDateMillis?.let {
                            date = it.toBrazilianDateFormat()
                            onDateSelected(Date(it))
                        }
                        openDateDialog = false
                    }
                ) {
                    Text("OK")
                }
            }
        ) {
            DatePicker(state = dateState)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DatePickerInputPreview() {
    DatePickerInput(onDateSelected = {})
}