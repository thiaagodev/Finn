package dev.thiaago.finn.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import dev.thiaago.finn.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onFinishSplashAnimation: () -> Unit) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.splash_animation
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LaunchedEffect(key1 = true) {
        delay(2000)
        onFinishSplashAnimation()
    }

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress
    )
}