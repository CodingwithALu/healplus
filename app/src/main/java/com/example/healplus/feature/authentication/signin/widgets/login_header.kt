package com.example.healplus.feature.authentication.signin.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.healplus.R
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun LoginHeader(){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        // App Logo
        Image(
            painter = painterResource(id = R.drawable.logo_app),
            contentDescription = "App Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(width = TSizes.IMAGE_THUMB_SIZE + 30.dp, height = (TSizes.IMAGE_THUMB_SIZE + 30.dp)/0.85f)
        )
        Spacer(modifier = Modifier.height(TSizes.MD))

        // Welcome to Title
        Text(
            text = stringResource(R.string.welcome_back),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = TSizes.FONT_SIZE_XL
            ),
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE/2))
        // Subtitle
        Text(
            text = "Khám phá vo vàng lựa chọn và sự tiện lợi vượt trội.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Start
        )
    }
}
