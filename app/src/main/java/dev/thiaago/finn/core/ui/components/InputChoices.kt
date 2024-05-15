package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputChoices(
    placeholder: String = "",
    title: String = "",
    items: List<String>,
    onSelected: (value: String, index: Int) -> Unit = { _: String, _: Int -> },
) {

    var openChoicesBottomSheet by remember {
        mutableStateOf(false)
    }
    val showClearIcon by remember {
        mutableStateOf(false)
    }
    var choice by remember {
        mutableStateOf("")
    }

    if (openChoicesBottomSheet) {

        ModalBottomSheet(
            containerColor = Color.White,
            tonalElevation = 0.dp,
            windowInsets = WindowInsets(0.dp),
            onDismissRequest = { openChoicesBottomSheet = false }
        ) {
            ChoicesBottomSheet(
                items = items,
                title = title,
                onSelected = { value, index ->
                    choice = value
                    onSelected(value, index)
                },
                onClose = {
                    openChoicesBottomSheet = false
                }
            )
        }
    }

    BasicTextField(
        modifier = Modifier
            .wrapContentWidth()
            .defaultMinSize(minWidth = 250.dp)
            .clickable { openChoicesBottomSheet = true },
        value = choice,
        onValueChange = {},
        maxLines = 1,
        singleLine = true,
        enabled = false
    ) {
        OutlinedTextFieldDefaults.DecorationBox(
            value = choice,
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
            contentPadding = PaddingValues(12.dp),
            visualTransformation = VisualTransformation.None,
            trailingIcon = {
                if (showClearIcon && choice.isNotEmpty()) {
                    Icon(
                        modifier = Modifier.clickable {
                            choice = ""
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InputChoicesPreview() {
    InputChoices(
        items = listOf("Teste"),
        placeholder = "Teste",
    )
}
