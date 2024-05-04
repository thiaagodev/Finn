package dev.thiaago.finn.core.ui.navigation

import android.widget.Space
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.thiaago.finn.R
import dev.thiaago.finn.core.ui.components.HomeBottomAppBar

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
                HomeBottomAppBar()
            },
        )
    }
}