package com.example.healplus.feature.personalization.profiles.widgets

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.healplus.R

@Composable
fun TEditButtonApp(onClick: () -> Unit) {
    IconButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(top = 16.dp)
            .height(56.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.edit_24px),
            contentDescription = null
        )
    }
}