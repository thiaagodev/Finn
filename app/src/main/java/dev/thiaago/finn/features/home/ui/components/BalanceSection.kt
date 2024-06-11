package dev.thiaago.finn.features.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.jetpackbrazilfields.extensions.toBrazilianCurrency


@Composable
fun BalanceSection(balance: Double) {
    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column {
                Text(
                    "Saldo geral",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    balance.toBrazilianCurrency(),
                    color = if(balance > 0) FinnColors.moneyColor else FinnColors.errorColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun IncomeAndExpensesSectionPreview() {
    BalanceSection(3000.89)
}