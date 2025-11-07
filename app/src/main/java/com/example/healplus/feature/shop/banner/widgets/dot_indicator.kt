package com.example.healplus.feature.shop.banner.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.healplus.R

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.purple_500),
    unSelectedColor: Color = colorResource(R.color.grey),
    dotHeight: Dp,
    dotWidthOff: Dp,
    dotWidth: Dp,
    dotShape: Shape = RoundedCornerShape(50)
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                height = dotHeight,
                width = if (index == selectedIndex) dotWidth else dotWidthOff,
                shape = dotShape
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    height: Dp,
    width: Dp,
    shape: Shape,
    color: Color
) {
    Box(
        modifier = modifier
            .height(height)
            .width(width)
            .clip(shape)
            .background(color = color)
    )
}