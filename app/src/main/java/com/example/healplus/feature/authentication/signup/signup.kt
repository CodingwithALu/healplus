package com.example.healplus.feature.authentication.signup

import FormDivider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.healplus.R
import com.example.healplus.feature.authentication.signup.widgets.SignupForm
import com.example.healplus.feature.common.widgets.login_signup.SocialButtons
import com.example.healplus.feature.utils.constants.TSizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { TopAppBar(title = {}) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = TSizes.DEFAULT_SPACE)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = "App Logo",
                modifier = Modifier.size(TSizes.IMAGE_THUMB_SIZE)
            )
            Spacer(modifier = Modifier.height(TSizes.MD))
            // Title
            Text(
                text = "Let’s create your account",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(TSizes.SPACE_BTW_SECTIONS))
            // Signup Form
            SignupForm(
                navController
            )
            Spacer(Modifier.height(TSizes.SPACE_BTW_SECTIONS))
            // Divider
            FormDivider(text = "or sign up with")
            Spacer(Modifier.height(TSizes.SPACE_BTW_SECTIONS))
            // Social Buttons
            SocialButtons(navController = navController)
        }
    }
}