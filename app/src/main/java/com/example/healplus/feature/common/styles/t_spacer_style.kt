package com.example.healplus.feature.common.styles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun TSpacerStyle(
    size: Dp
    ) {
    Box(modifier = Modifier.padding()) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(size)
        )
    }
}