package dev.thiaago.finn.core.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.thiaago.finn.features.home.ui.screens.HomeScreen
import dev.thiaago.finn.features.home.ui.viewmodels.AccountViewModel


const val homeRoute = "home"

fun NavGraphBuilder.homeScreen(accountViewModel: AccountViewModel) {
    composable(homeRoute) {
        HomeScreen(accountViewModel = accountViewModel)
    }
}