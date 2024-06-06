package dev.thiaago.finn.features.home.ui.components.homeHost

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import dev.thiaago.finn.core.ui.components.CustomOutlinedTextField
import dev.thiaago.finn.core.ui.components.DatePickerInput
import dev.thiaago.finn.core.ui.components.InputChoices
import dev.thiaago.finn.core.ui.components.InstallmentsBottomSheet
import dev.thiaago.finn.core.ui.components.SimpleButton
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseType
import dev.thiaago.finn.features.home.domain.entities.RepeatReleaseMode
import dev.thiaago.finn.features.home.ui.viewmodels.ExpenseFormViewModel
import dev.thiaago.jetpackbrazilfields.ui.visualtransformations.MoneyVisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseForm(
    accounts: List<AccountEntity> = listOf(),
    releaseType: ReleaseType,
    onConfirm: (release: ReleaseEntity) -> Unit = {},
) {
    val context = LocalContext.current
    val viewModel = hiltViewModel<ExpenseFormViewModel>()

    Column(
        Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        BasicTextField(
            modifier = Modifier.align(Alignment.End),
            value = viewModel.valueMoney,
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
                    viewModel.valueMoney = it
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
                CustomOutlinedTextField(
                    label = "Descrição",
                    material3Label = true,
                    placeholder = "",
                    fieldState = viewModel.descriptionState,
                    showClearIcon = true
                )

                InputChoices(
                    items = accounts.map { it.name },
                    placeholder = if (releaseType == ReleaseType.EXPENSE) "Pago com" else "Recebido em",
                    title = "Selecionar conta",
                    onSelected = { _, index ->
                        viewModel.accountState = accounts[index]
                    },
                )

                DatePickerInput(
                    onDateSelected = { date ->
                        viewModel.dateState = date
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


            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                InputChip(
                    selected = viewModel.releaseTypeState == RepeatReleaseMode.FIXED,
                    shape = RoundedCornerShape(16.dp),
                    colors = InputChipDefaults.inputChipColors(
                        disabledLabelColor = Color.Black,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = Color.White,
                        selectedContainerColor = FinnColors.lightGreen
                    ),
                    onClick = {
                        viewModel.installments = 0
                        viewModel.releaseTypeState =
                            if (viewModel.releaseTypeState != RepeatReleaseMode.FIXED) {
                                RepeatReleaseMode.FIXED
                            } else {
                                RepeatReleaseMode.NO_REPEAT
                            }
                    },
                    label = {
                        Text(text = "Fixo")
                    }
                )



                if (viewModel.showInstallmentsBottomSheet) {
                    ModalBottomSheet(
                        windowInsets = WindowInsets(0.dp),
                        containerColor = Color.White,
                        tonalElevation = 0.dp,
                        onDismissRequest = {
                            viewModel.showInstallmentsBottomSheet = false
                        }
                    ) {
                        InstallmentsBottomSheet(
                            currentInstallments = viewModel.installments,
                            onSelected = {
                                viewModel.showInstallmentsBottomSheet = false
                                viewModel.releaseTypeState = RepeatReleaseMode.INSTALLMENTS
                                viewModel.installments = it
                            }
                        )
                    }
                }
                InputChip(
                    selected = viewModel.releaseTypeState == RepeatReleaseMode.INSTALLMENTS,
                    colors = InputChipDefaults.inputChipColors(
                        disabledLabelColor = Color.Black,
                        selectedLabelColor = Color.White,
                        disabledContainerColor = Color.White,
                        selectedContainerColor = FinnColors.lightGreen
                    ),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        viewModel.showInstallmentsBottomSheet = true
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
                    val validateMessage = viewModel.validate()
                    validateMessage?.let {
                        Toast.makeText(
                            context,
                            validateMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    if (validateMessage.isNullOrEmpty()) {
                        onConfirm(viewModel.getReleaseEntity(releaseType))
                        viewModel.clearForm()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpenseFormPreview() {
    ExpenseForm(releaseType = ReleaseType.INCOME)
}