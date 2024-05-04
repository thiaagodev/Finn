package dev.thiaago.finn.core.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.thiaago.finn.features.login.ui.screens.LoginScreen

const val loginRoute = "login"

fun NavGraphBuilder.loginScreen(onNavigateToHome: () -> Unit) {
    composable(loginRoute) {
        LoginScreen(
            onNavigateToHome = onNavigateToHome
        )
    }
}