package dev.thiaago.finn.features.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.jetpackbrazilfields.extensions.centsToRealDouble
import dev.thiaago.jetpackbrazilfields.extensions.toBrazilianCurrency

@Composable
fun MyAccountsCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp

    Box(
        modifier
            .height((screenHeight * 0.5).dp)
            .padding(horizontal = 32.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            shadowElevation = 4.dp,
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
                    .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(FinnColors.darkGreen)
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        "Minhas contas",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(Modifier.weight(1f))
                    FilledIconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.filledIconButtonColors(containerColor = FinnColors.darkGreen),
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Outlined.AddCircle,
                            contentDescription = "Adicionar conta",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                content()
            }
        }
    }
}

@Composable
fun AccountItem(account: AccountEntity, showDivider: Boolean) {
    Column(
        Modifier.padding(
            top = 16.dp, start = 16.dp, end = 16.dp
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = account.name, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            )
            Text(
                text = account.balance.centsToRealDouble().toBrazilianCurrency(),
                style = TextStyle(
                    color = if (account.balance > 0)
                        FinnColors.moneyColor else FinnColors.errorColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            )
        }

        if (showDivider) {
            HorizontalDivider(
                color = FinnColors.moneyColor,
                thickness = 2.dp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Preview
@Composable
private fun MyAccountsCardPrev() {
    MyAccountsCard(
        Modifier,
        content = {
            val accountList = listOf(
                AccountEntity(
                    name = "Teste", balance = 1200
                ), AccountEntity(
                    name = "Teste", balance = -1200
                )
            )

            accountList.forEachIndexed() { index, account ->
                AccountItem(
                    account = account, showDivider = index < (accountList.size - 1)
                )
            }
        },
    )
}