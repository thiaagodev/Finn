package dev.thiaago.finn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.thiaago.finn.core.ui.theme.FinnTheme
import dev.thiaago.finn.features.home.ui.pages.HomePage

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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
                                    HomePage(navController)
                                }
                            }
                        },
                        bottomBar = {
                            BottomAppBar(
                                containerColor = MaterialTheme.colorScheme.surface,
                                tonalElevation = 0.dp,
                                contentPadding = PaddingValues(2.dp),
                                actions = {
                                    NavigationBarItem(
                                        modifier = Modifier.padding(start = 16.dp),
                                        onClick = { /*TODO*/ },
                                        selected = false,
                                        icon = {
                                            Icon(
                                                modifier = Modifier.size(32.dp),
                                                imageVector = Icons.Filled.Home,
                                                contentDescription = "Home"
                                            )
                                        }
                                    )

                                    NavigationBarItem(
                                        modifier = Modifier.padding(start = 16.dp),
                                        onClick = { /*TODO*/ },
                                        selected = false,
                                        icon = {
                                            Icon(
                                                modifier = Modifier.size(32.dp),
                                                painter = painterResource(id = R.drawable.outline_payments_24),
                                                contentDescription = "Gastos e despesas"
                                            )
                                        }
                                    )

                                },
                                floatingActionButton = {
                                    FloatingActionButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            imageVector = Icons.Filled.Add,
                                            contentDescription = "Adicionar Gasto ou Despesa",
                                            tint = MaterialTheme.colorScheme.onPrimary,
                                        )
                                    }
                                }
                            )
                        },
                    )


                }
            }
        }
    }
}