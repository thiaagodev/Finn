package dev.thiaago.finn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import dev.thiaago.finn.core.ui.components.SplashScreen
import dev.thiaago.finn.core.ui.navigation.homeGraph
import dev.thiaago.finn.core.ui.navigation.loginRoute
import dev.thiaago.finn.core.ui.navigation.loginScreen
import dev.thiaago.finn.core.ui.navigation.navigateToHomeGraphAndReplace
import dev.thiaago.finn.core.ui.navigation.navigateToLogin
import dev.thiaago.finn.core.ui.theme.FinnTheme

const val splashRoute = "splash"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loggedUser = FirebaseAuth.getInstance().currentUser

        setContent {
            FinnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    Scaffold(
                        content = { paddingValues ->
                            NavHost(
                                modifier = Modifier.padding(paddingValues),
                                navController = navController,
                                startDestination = splashRoute,
                            ) {
                                splashScreen(
                                    onFinishSplashAnimation = {
                                        if (loggedUser == null) {
                                            navController.navigateToLogin()
                                        } else {
                                            navController.navigateToHomeGraphAndReplace(splashRoute)
                                        }

                                    }
                                )
                                loginScreen(onNavigateToHome = {
                                    navController.navigateToHomeGraphAndReplace(loginRoute)
                                })
                                homeGraph()
                            }
                        },
                    )


                }
            }
        }
    }

    private fun NavGraphBuilder.splashScreen(
        onFinishSplashAnimation: () -> Unit
    ) {
        composable(splashRoute) {
            SplashScreen(
                onFinishSplashAnimation = onFinishSplashAnimation
            )
        }
    }
}