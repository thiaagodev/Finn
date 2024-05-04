package dev.thiaago.finn.features.home.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import dev.thiaago.finn.core.extensions.firstWord
import dev.thiaago.finn.core.extensions.isDay
import dev.thiaago.finn.core.ui.theme.FinnTheme
import dev.thiaago.finn.features.home.ui.components.IncomeAndExpensesSection
import dev.thiaago.finn.features.home.ui.components.MyAccountsCard
import java.time.LocalDateTime

@Composable
fun HomeScreen() {
    val loggedUser = FirebaseAuth.getInstance().currentUser

    FinnTheme {
        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primaryContainer,
                            )
                        )
                    )
            ) {
                Column(Modifier.padding(32.dp)) {
                    val welcomeText = if (LocalDateTime.now().isDay() ) "Bom dia" else "Boa noite"

                    Text(
                        "$welcomeText,\n${loggedUser?.displayName?.firstWord()}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    IncomeAndExpensesSection(income = 3000.89, expenses = 2400.55)
                }
            }
            MyAccountsCard(Modifier.offset(y = 200.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePagePreview() {
    HomeScreen()
}