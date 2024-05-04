package dev.thiaago.finn.core.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.thiaago.finn.features.login.ui.screens.LoginScreen
import dev.thiaago.finn.splashRoute

const val loginRoute = "login"

fun NavHostController.navigateToLogin() {
    navigate(loginRoute) {
        popUpTo(splashRoute) {inclusive = true}
    }
}

fun NavGraphBuilder.loginScreen(onNavigateToHome: () -> Unit) {
    composable(loginRoute) {
        LoginScreen(
            onNavigateToHome = onNavigateToHome
        )
    }
}