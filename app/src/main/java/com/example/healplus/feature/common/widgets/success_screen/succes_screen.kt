package com.example.healplus.feature.common.widgets.success_screen

// You will need a Lottie Compose library for animation support.
// For this mock, we'll use a Box as a placeholder for the Lottie animation.
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SuccessScreen(
    image: String,
    title: String,
    subtitle: String,
    showEmail: Boolean = true,
    onContinue: () -> Unit,
    onResendEmail: (() -> Unit)? = null,
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.Asset(image))
    val lottieProgress by animateLottieCompositionAsState(lottieComposition)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp), // adjust as needed
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Animation
        Box(
            modifier = Modifier
                .width(screenWidth * 0.6f)
                .height(screenWidth * 0.6f) // keep the box square
        ) {
            LottieAnimation(
                composition = lottieComposition,
                progress = { lottieProgress }
            )
        }

        Spacer(Modifier.height(24.dp))

        // Title
        Text(
            title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        Text(
            subtitle,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        // Continue Button
        Button(
            onClick = onContinue,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue")
        }

        Spacer(Modifier.height(24.dp))

        // Resend Email Button
        if (showEmail) {
            TextButton(
                onClick = { onResendEmail?.invoke() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Resend email")
            }
        }
    }
}