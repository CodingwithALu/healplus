package com.example.healplus.feature.utils.loaders
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core_utils.utils.constants.TSizes

/**
 * A circular loader widget with customizable foreground and background colors.
 *
 * @param modifier Modifier to be applied to the root composable
 * @param foregroundColor The color of the circular loader
 * @param backgroundColor The background color of the circular loader
 * @param size The size of the circular background container
 * @param strokeWidth The width of the circular progress indicator
 */
@Composable
fun CircularLoader(
    modifier: Modifier = Modifier,
    foregroundColor: Color = Color.White,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    size: Dp = 56.dp,
    strokeWidth: Dp = 4.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(TSizes.LG),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = foregroundColor,
            strokeWidth = strokeWidth,
            modifier = Modifier.fillMaxSize()
        )
    }
}