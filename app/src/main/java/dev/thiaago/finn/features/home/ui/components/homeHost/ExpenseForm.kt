package dev.thiaago.finn.features.home.ui.components.homeHost

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import dev.thiaago.finn.core.ui.components.CustomOutlinedTextField
import dev.thiaago.finn.core.ui.components.DatePickerInput
import dev.thiaago.finn.core.ui.components.InputChoices
import dev.thiaago.finn.core.ui.components.SimpleButton
import dev.thiaago.finn.core.ui.state.FieldState
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseType
import dev.thiaago.jetpackbrazilfields.ui.visualtransformations.MoneyVisualTransformation
import java.util.Date

@Composable
fun ExpenseForm(
    accounts: List<AccountEntity> = listOf(),
    onConfirm: () -> Unit = {},
) {
    var valueMoney by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        BasicTextField(
            modifier = Modifier.align(Alignment.End),
            value = valueMoney,
            visualTransformation = MoneyVisualTransformation(currencySymbol = "R$"),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            textStyle = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
            ),
            onValueChange = {
                if (it.isDigitsOnly()) {
                    valueMoney = it
                }
            }
        )

        Column(
            Modifier.padding(top = 32.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Column(
                Modifier.fillMaxWidth(0.75f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val descriptionState = FieldState(field = "")
                CustomOutlinedTextField(
                    label = "Descrição",
                    material3Label = true,
                    placeholder = "",
                    fieldState = descriptionState,
                    showClearIcon = true
                )

                var accountState by remember {
                    mutableStateOf("")
                }
                InputChoices(
                    items = accounts.map { it.name },
                    placeholder = "Pago com",
                    title = "Selecionar conta",
                    onSelected = { value, index ->
                        accountState = value
                    },
                )

                var dateState by remember {
                    mutableStateOf<Date?>(null)
                }
                DatePickerInput(
                    onDateSelected = { date ->
                        dateState = date
                    }
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
                color = FinnColors.moneyColor,
                thickness = 2.dp
            )

            Text(
                text = "Repetir lançamento",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            var releaseTypeState by remember {
                mutableStateOf(ReleaseType.NORMAL)
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                InputChip(
                    selected = releaseTypeState == ReleaseType.FIXED,
                    shape = RoundedCornerShape(16.dp),
                    colors = InputChipDefaults.inputChipColors(
                        disabledLabelColor = Color.Black,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = Color.White,
                        selectedContainerColor = FinnColors.lightGreen
                    ),
                    onClick = {
                        releaseTypeState = if (releaseTypeState != ReleaseType.FIXED) {
                            ReleaseType.FIXED
                        } else {
                            ReleaseType.NORMAL
                        }
                    },
                    label = {
                        Text(text = "Fixo")
                    }
                )

                InputChip(
                    selected = releaseTypeState == ReleaseType.INSTALLMENTS,
                    colors = InputChipDefaults.inputChipColors(
                        disabledLabelColor = Color.Black,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = Color.White,
                        selectedContainerColor = FinnColors.lightGreen
                    ),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        releaseTypeState = if (releaseTypeState != ReleaseType.INSTALLMENTS) {
                            ReleaseType.INSTALLMENTS
                        } else {
                            ReleaseType.NORMAL
                        }
                    },
                    label = {
                        Text(text = "Parcelado")
                    }
                )
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp)
            ) {
                SimpleButton(
                    Modifier.align(Alignment.BottomCenter),
                    text = "Confimar"
                ) {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseFormPreview() {
    ExpenseForm()
}