package dev.thiaago.finn.core.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.thiaago.finn.R

private const val homeGraphRoute = "homeGraph"

fun NavHostController.navigateToHomeGraphAndReplace(replacedRoute: String) {
    navigate(homeGraphRoute) {
        popUpTo(replacedRoute) { inclusive = true }
    }
}

fun NavGraphBuilder.homeGraph() {
    composable(route = homeGraphRoute) {
        Scaffold(
            content = {
                NavHost(
                    navController = rememberNavController(),
                    startDestination = homeRoute,
                    Modifier.padding(it)
                ) {
                    homeScreen()
                }
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    tonalElevation = 0.dp,
                    modifier = Modifier.height(64.dp),
                    actions = {
                        NavigationBarItem(
                            modifier = Modifier.padding(start = 16.dp),
                            onClick = { /*TODO*/ },
                            selected = false,
                            icon = {
                                Icon(
                                    modifier = Modifier.size(32.dp),
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Home"
                                )
                            }
                        )
                        NavigationBarItem(
                            modifier = Modifier.padding(start = 0.dp),
                            onClick = { /*TODO*/ },
                            selected = false,
                            icon = {
                                Icon(
                                    modifier = Modifier.size(32.dp),
                                    painter = painterResource(id = R.drawable.outline_payments_24),
                                    contentDescription = "Receitas e despesas"
                                )
                            }
                        )

                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            onClick = { /*TODO*/ },
                            modifier = Modifier.size(48.dp),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Adicionar Gasto ou Despesa",
                                tint = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }
                )
            },
        )
    }
}