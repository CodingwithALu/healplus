package com.example.healplus.feature.shop.product.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PriceText(model: String,
              selected: Boolean,
              onclick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(46.dp)
            .width(80.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) Color.Gray else Color.LightGray)
            .clickable(
                onClick = {
                    onclick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = model,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007AFF),
        )
    }
}