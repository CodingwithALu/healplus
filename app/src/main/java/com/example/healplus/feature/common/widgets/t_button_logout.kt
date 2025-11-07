package com.example.healplus.feature.common.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TButtonLogOut(
    onClick: () -> Unit,
    @StringRes title: Int,
    ) {
    Text(
        text = stringResource(title),
        fontSize = 16.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {onClick()}
            .padding(top = 24.dp),
        textAlign = TextAlign.Center
    )
}