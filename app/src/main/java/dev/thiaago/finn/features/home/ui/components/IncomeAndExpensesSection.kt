package dev.thiaago.finn.features.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.jetpackbrazilfields.extensions.toBrazilianCurrency


@Composable
fun IncomeAndExpensesSection(income: Double, expenses: Double) {
    Column(
        Modifier
            .padding(32.dp)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    "Receitas",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                )
                Text(
                    income.toBrazilianCurrency(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            HorizontalDivider(
                Modifier
                    .width(2.dp)
                    .height(64.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
            Column {
                Text(
                    "Despesas",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                )
                Text(
                    expenses.toBrazilianCurrency(),
                    color = MaterialTheme.colorScheme.onPrimary,
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
    IncomeAndExpensesSection(3000.89, 2400.55)
}