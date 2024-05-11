package dev.thiaago.finn.core.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.thiaago.finn.core.ui.components.CreateExpenseBottomSheet
import dev.thiaago.finn.core.ui.components.HomeBottomAppBar

private const val homeGraphRoute = "homeGraph"

fun NavHostController.navigateToHomeGraphAndReplace(replacedRoute: String) {
    navigate(homeGraphRoute) {
        popUpTo(replacedRoute) { inclusive = true }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.homeGraph() {
    composable(route = homeGraphRoute) {
        var showAddExpenseBottomSheet by remember {
            mutableStateOf(false)
        }

        if(showAddExpenseBottomSheet) {
            ModalBottomSheet(
                sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true,
                ),
                containerColor = Color.White,
                tonalElevation = 0.dp,
                onDismissRequest = { showAddExpenseBottomSheet = false }
            ) {
                CreateExpenseBottomSheet(
                    onConfirm = {},
                    onClose = {}
                )
            }
        }

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
                HomeBottomAppBar(
                    onAddExpensePressed = {
                        showAddExpenseBottomSheet = true
                    }
                )
            },
        )
    }
}