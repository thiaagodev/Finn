package dev.thiaago.finn.features.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.thiaago.finn.core.ui.theme.darkGreen

@Composable
fun MyAccountsCard() {
    Box {
        Surface(
            shape = RoundedCornerShape(15.dp),
            shadowElevation = 4.dp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 32.dp)
                .offset(y = 200.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        "Minhas contas",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    FilledIconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = darkGreen),
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 8.dp)
                            .size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Adicionar conta",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                Divider(color = darkGreen, thickness = 2.dp)

            }
        }
    }
}

@Preview
@Composable
private fun MyAccountsCardPrev() {

}