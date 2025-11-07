package com.example.healplus.feature.common.widgets

import android.net.Uri
import android.support.annotation.StringRes
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun TAvatarImage(
    uploadedImageUrls: String?,
    imagePickerLauncher: ManagedActivityResultLauncher<String, Uri?>? = null,
    @StringRes label: Int? = null,
    size: Dp = 100.dp,
    showChangeImage: Boolean = true
) {
    Image(
        painter = if (uploadedImageUrls != null) rememberAsyncImagePainter(uploadedImageUrls)
        else painterResource(label!!),
        contentDescription = "Chọn ảnh",
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .clickable {
                if (showChangeImage) imagePickerLauncher!!.launch("image/*")
            },
        contentScale = ContentScale.Crop
    )
}