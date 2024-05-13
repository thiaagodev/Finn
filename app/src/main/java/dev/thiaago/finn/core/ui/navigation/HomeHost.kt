package dev.thiaago.finn.core.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.thiaago.finn.core.ui.components.homeHost.CreateExpenseBottomSheet
import dev.thiaago.finn.core.ui.components.homeHost.HomeBottomAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeHost() {
    var showAddExpenseBottomSheet by remember {
        mutableStateOf(false)
    }

    if (showAddExpenseBottomSheet) {
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