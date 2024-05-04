package dev.thiaago.finn.core.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.thiaago.finn.features.home.ui.screens.HomeScreen

const val homeRoute = "home"
fun NavGraphBuilder.homeScreen() {
    composable(homeRoute) {
        HomeScreen()
    }
}

fun NavHostController.navigateToHomeAndReplace() {
    navigate(homeRoute) {
        popUpTo(loginRoute) { inclusive = true }
    }
}