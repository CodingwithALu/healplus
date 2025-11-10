package com.example.healplus.feature.common.widgets.texts

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.healplus.R
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun TSectionHeading(
    @StringRes title: Int,
    @StringRes subtitle: Int = R.string.see_all,
    showSubtitle: Boolean = true,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = TSizes.DEFAULT_SPACE/2),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = title),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        if (showSubtitle) {
            Text(
                text = stringResource(id = subtitle),
                fontSize = 12.sp,
                modifier = Modifier.clickable(
                    onClick = onClick,
                ),
            )
        }

    }
}