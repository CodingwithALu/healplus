package com.example.healplus.feature.common.shimmer
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun TShimmerEffect(
    modifier: Modifier = Modifier,
    clipSize: Dp = TSizes.LG,
    color: Color? = null
) {
    val dark = isSystemInDarkTheme()
    val baseColor = if (dark) Color(0xFF212121) else Color(0xFFE0E0E0)
    val highlightColor = if (dark) Color(0xFF616161) else Color(0xFFF5F5F5)
    val bgColor = color ?: if (dark) Color(0xFF323232) else Color.White

    // Animation for shimmer
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "shimmer_anim"
    )
    val brush = Brush.linearGradient(
        colors = listOf(baseColor, highlightColor, baseColor),
        start = Offset(translateAnim.value - 1000f, 0f),
        end = Offset(translateAnim.value, 0f)
    )
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(clipSize))
            .background(brush)

    )
}
