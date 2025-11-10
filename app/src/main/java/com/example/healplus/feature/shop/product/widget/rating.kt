package com.example.healplus.feature.shop.product.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.healplus.R
import kotlin.math.floor

@Composable
fun RatingBar(
    rating: Float,
    modifier: Modifier = Modifier,
    starCount: Int = 5,
    starSize: Dp = 24.dp,
    starColor: Color = Color(0xFFFFC107),
    emptyStarColor: Color = Color.LightGray
) {
    Row(modifier = modifier) {
        val filledStars = floor(rating).toInt()
        val halfStar = rating - filledStars >= 0.5f
        val emptyStars = starCount - filledStars - if (halfStar) 1 else 0
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(starSize)
            )
        }
        if (halfStar) {
            Icon(
                painter = painterResource(R.drawable.star_half_24px),
                contentDescription = null,
                tint = starColor,
                modifier = Modifier.size(starSize)
            )
        }
        repeat(emptyStars) {
            Icon(
                painter = painterResource(R.drawable.star_24px),
                contentDescription = null,
                tint = emptyStarColor,
                modifier = Modifier.size(starSize)
            )
        }
    }
}