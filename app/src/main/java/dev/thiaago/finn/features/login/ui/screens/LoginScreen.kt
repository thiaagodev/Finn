package dev.thiaago.finn.features.login.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.R
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.finn.core.ui.theme.FinnTheme

@Composable
fun LoginScreen() {
    FinnTheme {
        val colorScheme = MaterialTheme.colorScheme
        Scaffold() { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                colorScheme.primary,
                                colorScheme.primaryContainer,
                            )
                        )
                    )
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Finn",
                    style = TextStyle(
                        color = colorScheme.onPrimary,
                        fontSize = 64.sp,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Seja bem vindo(a)",
                    style = TextStyle(
                        color = colorScheme.onPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Para começar, faça login com sua conta Google",
                    style = TextStyle(
                        color = colorScheme.onPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.padding(horizontal = 64.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                ElevatedButton(
                    onClick = {},
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "Login com Google",
                            Modifier.size(20.dp)
                        )
                        Text(
                            text = "Entrar com Google", style = TextStyle(
                                color = FinnColors.gray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                            ),
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}