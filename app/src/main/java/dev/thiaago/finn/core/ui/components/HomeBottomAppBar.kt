package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.thiaago.finn.R
import dev.thiaago.finn.core.ui.theme.FinnTheme

@Composable
fun HomeBottomAppBar() {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 0.dp,
        modifier = Modifier.height(64.dp),
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.drawable.outline_payments_24),
                    contentDescription = "Home"
                )
            }
        },
        floatingActionButton = {
            SmallFloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Adicionar Gasto ou Despesa",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    )
}

@Preview
@Composable
private fun HomeBottomAppBarPreview() {
    FinnTheme {
        HomeBottomAppBar()
    }
}