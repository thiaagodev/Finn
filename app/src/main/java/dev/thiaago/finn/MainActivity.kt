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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dev.thiaago.finn.core.ui.theme.FinnTheme
import dev.thiaago.finn.features.home.ui.screens.HomeScreen
import dev.thiaago.finn.features.login.ui.screens.LoginScreen

class MainActivity : ComponentActivity() {

    private fun getGoogleLoginAuth(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.googleServerID))
            .build()

        return GoogleSignIn.getClient(this, gso)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                                startDestination = "/",
                            ) {
                                composable("/") {
                                    LoginScreen(
                                        googleSignInClient = getGoogleLoginAuth(),
                                        navigateToHome = {
                                            navController.navigate("/home")
                                            navController.clearBackStack("/")
                                        }
                                    )
                                }
                                composable("/home") {
                                    HomeScreen()
                                }
                            }
                        },
//                        bottomBar = {
//                            BottomAppBar(
//                                containerColor = MaterialTheme.colorScheme.surface,
//                                tonalElevation = 0.dp,
//                                actions = {
//                                    NavigationBarItem(
//                                        modifier = Modifier.padding(start = 16.dp),
//                                        onClick = { /*TODO*/ },
//                                        selected = false,
//                                        icon = {
//                                            Icon(
//                                                modifier = Modifier.size(24.dp),
//                                                imageVector = Icons.Filled.Home,
//                                                contentDescription = "Home"
//                                            )
//                                        }
//                                    )
//
//                                    NavigationBarItem(
//                                        modifier = Modifier.padding(start = 8.dp),
//                                        onClick = { /*TODO*/ },
//                                        selected = false,
//                                        icon = {
//                                            Icon(
//                                                modifier = Modifier.size(24.dp),
//                                                painter = painterResource(id = R.drawable.outline_payments_24),
//                                                contentDescription = "Receitas e despesas"
//                                            )
//                                        }
//                                    )
//
//                                },
//                                floatingActionButton = {
//                                    FloatingActionButton(onClick = { /*TODO*/ }) {
//                                        Icon(
//                                            imageVector = Icons.Filled.Add,
//                                            contentDescription = "Adicionar Gasto ou Despesa",
//                                            tint = MaterialTheme.colorScheme.onPrimary,
//                                        )
//                                    }
//                                }
//                            )
//                        },
                    )


                }
            }
        }
    }
}