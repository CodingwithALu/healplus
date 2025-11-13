package com.example.healplus.feature.authentication.signin.widgets

import android.support.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AutoSizeImage(
    @DrawableRes resId: Int,
    contentDescription: String
) {
    val context = LocalContext.current
    val drawable = remember(resId) {
        context.resources.getDrawable(resId, null)
    }
    val width = drawable.intrinsicWidth
    val height = drawable.intrinsicHeight

    Image(
        painter = painterResource(id = resId),
        contentDescription = contentDescription,
        modifier = Modifier.size(width.dp, height.dp),
        contentScale = ContentScale.Fit
    )
}