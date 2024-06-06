package dev.thiaago.finn.features.home.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.thiaago.finn.core.ui.navigation.homeRoute
import dev.thiaago.finn.core.ui.navigation.homeScreen
import dev.thiaago.finn.features.home.ui.components.homeHost.CreateExpenseBottomSheet
import dev.thiaago.finn.features.home.ui.components.homeHost.HomeBottomAppBar
import dev.thiaago.finn.features.home.ui.states.AccountState
import dev.thiaago.finn.features.home.ui.viewmodels.AccountViewModel
import dev.thiaago.finn.features.home.ui.viewmodels.ReleaseViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeHost() {
    val accountViewModel: AccountViewModel = hiltViewModel()
    val releaseViewModel: ReleaseViewModel = hiltViewModel()

    val accountState = accountViewModel.accountListState.collectAsState()

    var showAddExpenseBottomSheet by remember {
        mutableStateOf(false)
    }

    if (showAddExpenseBottomSheet) {
        accountViewModel.getAccountList()
        ModalBottomSheet(
            sheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true,
            ),
            containerColor = Color.White,
            tonalElevation = 0.dp,
            onDismissRequest = { showAddExpenseBottomSheet = false }
        ) {
            CreateExpenseBottomSheet(
                accounts = if (accountState.value is AccountState.GetListAccountSuccess) {
                    (accountState.value as AccountState.GetListAccountSuccess).accounts
                } else {
                    listOf()
                },
                onConfirm = {
                    releaseViewModel.createRelease(it)
                    showAddExpenseBottomSheet = false
                },
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