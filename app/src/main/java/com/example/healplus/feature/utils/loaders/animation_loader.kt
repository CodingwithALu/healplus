package com.example.healplus.feature.utils.loaders
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun AnimationLoaderWidget(
    text: String,
    modifier: Modifier = Modifier,
    animationAsset: String? = null,
    animationRes: Int? = null,
    showAction: Boolean = false,
    actionText: String? = null,
    onActionPressed: (() -> Unit)? = null
) {
    require(animationAsset != null || animationRes != null) {
        "Either animationAsset or animationRes must be provided"
    }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val animationWidth = screenWidth * 0.8f

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Lottie Animation
            val composition by rememberLottieComposition(
                when {
                    animationAsset != null -> LottieCompositionSpec.Asset(animationAsset)
                    animationRes != null -> LottieCompositionSpec.RawRes(animationRes)
                    else -> throw IllegalArgumentException("No animation provided")
                }
            )
            val progress by animateLottieCompositionAsState(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.width(animationWidth)
            )

            Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE))

            // Text
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE))

            // Action Button
            if (showAction && actionText != null && onActionPressed != null) {
                OutlinedButton(
                    onClick = onActionPressed,
                    modifier = Modifier.width(250.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(
                        text = actionText,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}