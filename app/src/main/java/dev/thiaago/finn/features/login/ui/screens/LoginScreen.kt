package dev.thiaago.finn.features.login.ui.screens

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import dev.thiaago.finn.R
import dev.thiaago.finn.core.ui.theme.FinnTheme
import dev.thiaago.finn.features.login.ui.components.LoginButton

@Composable
fun LoginScreen(onNavigateToHome: () -> Unit) {

    val activity = LocalContext.current as Activity

    fun getGoogleLoginAuth(): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .requestIdToken(activity.getString(R.string.googleServerID))
            .build()

        return GoogleSignIn.getClient(activity, gso)
    }

    val startForResult =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(result.data)

                task.addOnCompleteListener { googleSignInAccountTask ->
                    if (googleSignInAccountTask.isSuccessful) {
                        val account = googleSignInAccountTask.result
                        val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                        FirebaseAuth.getInstance().signInWithCredential(credential)
                            .addOnCompleteListener {
                                it.result.user?.updateProfile(
                                    UserProfileChangeRequest.Builder()
                                        .setDisplayName(account.displayName).build()
                                )

                                onNavigateToHome()
                            }
                    }
                }
            }
        }

    FinnTheme {
        val colorScheme = MaterialTheme.colorScheme
        Scaffold { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                colorScheme.primary,
                                colorScheme.primaryContainer,
                            )
                        )
                    )
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Finn",
                    style = TextStyle(
                        color = colorScheme.onPrimary,
                        fontSize = 64.sp,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Seja bem vindo(a)",
                    style = TextStyle(
                        color = colorScheme.onPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Para começar, faça login com sua conta Google",
                    style = TextStyle(
                        color = colorScheme.onPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.padding(horizontal = 64.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                LoginButton {
                    val googleSignInClient = getGoogleLoginAuth()
                    googleSignInClient.signInIntent.let {
                        startForResult.launch(it)
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(onNavigateToHome = {})
}