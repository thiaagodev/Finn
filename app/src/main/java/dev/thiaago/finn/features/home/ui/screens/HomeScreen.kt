package dev.thiaago.finn.features.home.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.firebase.auth.FirebaseAuth
import dev.thiaago.finn.core.extensions.firstWord
import dev.thiaago.finn.core.extensions.isDay
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.finn.core.ui.theme.FinnTheme
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.ui.components.AccountItem
import dev.thiaago.finn.features.home.ui.components.CreateAccountBottomSheet
import dev.thiaago.finn.features.home.ui.components.IncomeAndExpensesSection
import dev.thiaago.finn.features.home.ui.components.MyAccountsCard
import dev.thiaago.finn.features.home.ui.states.AccountState
import dev.thiaago.finn.features.home.ui.viewmodels.AccountViewModel
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(accountViewModel: AccountViewModel) {
    val loggedUser by remember {
        mutableStateOf(FirebaseAuth.getInstance().currentUser)
    }

    val accountListState = accountViewModel.accountListState.collectAsState()

    var showCreateAccountBottomSheet by remember {
        mutableStateOf(false)
    }

    FinnTheme {
        if (showCreateAccountBottomSheet) {
            ModalBottomSheet(
                containerColor = Color.White,
                tonalElevation = 0.dp,
                sheetState = rememberModalBottomSheetState(
                    skipPartiallyExpanded = true
                ),
                onDismissRequest = {
                    showCreateAccountBottomSheet = false
                },
                content = {
                    CreateAccountBottomSheet(
                        onClose = {
                            showCreateAccountBottomSheet = false
                        },
                        onConfirm = {accountName ->
                            accountViewModel.createAccount(
                                AccountEntity(
                                    ownerId = loggedUser?.uid,
                                    name = accountName
                                )
                            )
                        }
                    )
                }
            )

        }

        Box(Modifier.fillMaxSize()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primaryContainer,
                            )
                        )
                    )
            ) {
                Column(Modifier.padding(32.dp)) {
                    val welcomeText = if (LocalDateTime.now().isDay()) "Bom dia" else "Boa noite"

                    Text(
                        "$welcomeText,\n${loggedUser?.displayName?.firstWord() ?: ""}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    IncomeAndExpensesSection(income = 0.0, expenses = 0.0)
                }
            }

            MyAccountsCard(
                Modifier.offset(y = 200.dp),
                onAddAccountPressed = {
                    showCreateAccountBottomSheet = true
                },
                content = {
                    when (val accountState = accountListState.value) {
                        is AccountState.GetListAccountSuccess -> {
                            LazyColumn {
                                items(count = accountState.accounts.size) { index ->
                                    val account = accountState.accounts[index]
                                    AccountItem(
                                        account = account,
                                        showDivider = index < (accountState.accounts.size - 1)
                                    )
                                }
                            }
                        }

                        is AccountState.GetListAccountError -> {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Erro",
                                    tint = FinnColors.errorColor,
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Não foi possível carregar suas contas",
                                    style = TextStyle(
                                        fontSize = 14.sp
                                    ),
                                )
                            }
                        }

                        else -> {
                            Box(
                                Modifier.fillMaxSize()
                            ) {
                                CircularProgressIndicator(Modifier.align(Alignment.Center))
                            }
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePagePreview() {
    HomeScreen(hiltViewModel())
}