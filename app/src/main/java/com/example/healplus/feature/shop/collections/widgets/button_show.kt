package com.example.healplus.feature.shop.collections.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun ButtonShow(
    isExpanded: Boolean,
    event: (Boolean) -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = TSizes.SM/4),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isExpanded)
                Icons.Rounded.KeyboardArrowUp
            else
                Icons.Rounded.KeyboardArrowDown,
            contentDescription = if (isExpanded) "Show less" else "Show more",
            modifier = Modifier
                .clip(CircleShape)
                .clickable(
                onClick = { event(!isExpanded) }
            )
        )
    }
}