package dev.thiaago.finn.core.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val homeGraphRoute = "homeGraph"

fun NavHostController.navigateToHomeGraphAndReplace(replacedRoute: String) {
    navigate(homeGraphRoute) {
        popUpTo(replacedRoute) { inclusive = true }
    }
}

fun NavGraphBuilder.homeGraph() {
    composable(route = homeGraphRoute) {
        HomeHost()
    }
}