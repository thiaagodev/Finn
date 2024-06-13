package dev.thiaago.finn.features.cashflow.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Timestamp
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.finn.features.home.domain.entities.ReleasePayment
import dev.thiaago.finn.features.home.domain.entities.ReleaseType

@Composable
fun ReleaseCard(
    modifier: Modifier = Modifier,
    releaseType: ReleaseType = ReleaseType.INCOME,
    releasePayment: ReleasePayment? = null,
) {

    fun getReleaseTextColor() = when (releaseType) {
        ReleaseType.EXPENSE -> FinnColors.errorColor
        ReleaseType.INCOME -> FinnColors.moneyColor
    }

    fun getStatusText(): String {
        if (releasePayment == null || !releasePayment.payed) {
            return "Pendente"
        }

       return when(releaseType) {
           ReleaseType.EXPENSE -> "Pago"
           ReleaseType.INCOME -> "Recebido"
       }
    }

    Card(
        modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when (releaseType) {
                ReleaseType.EXPENSE -> FinnColors.opacityRed
                ReleaseType.INCOME -> FinnColors.opacityGreen
            }
        )
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Salário",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "2.300,00",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = getReleaseTextColor()
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = "Inter",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )
                Text(
                    text = getStatusText(), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = getReleaseTextColor()
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ReleaseCardPreview() {
    ReleaseCard(
        Modifier.padding(16.dp),
        ReleaseType.EXPENSE,
        ReleasePayment(date = Timestamp.now(), payed = true)
    )
}