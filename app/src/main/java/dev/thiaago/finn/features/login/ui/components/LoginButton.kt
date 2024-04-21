package dev.thiaago.finn.features.login.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.R
import dev.thiaago.finn.core.ui.theme.FinnColors

@Composable
fun LoginButton(onClick: () -> Unit) {
    ElevatedButton(
        onClick = onClick,
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

@Preview
@Composable
private fun LoginButtonPreview() {
    LoginButton {

    }
}