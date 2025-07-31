package com.example.healplus.common.widgets
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TAppBar(
    @StringRes title: Int,
    onClick: () -> Unit,
    showBackArrow: Boolean = false)
    {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(title),
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
            )
        },
        navigationIcon = {
            IconButton(onClick = {onClick()}) {
                if (showBackArrow) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft , contentDescription = null)
                }
            }
        }
    )
}