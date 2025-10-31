package com.example.healplus.feature.authentication.signin
import FormDivider
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.viewmodel.authviewmodel.LoginViewModel
import com.example.healplus.R
import com.example.healplus.feature.authentication.signin.widgets.LoginForm
import com.example.healplus.feature.authentication.signin.widgets.LoginHeader
import com.example.healplus.feature.common.widgets.login_signup.SocialButtons
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun SignInScreen(
    navController: NavController
) {
    val viewModel: LoginViewModel = hiltViewModel()
    val message by viewModel.message.collectAsState()
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = TSizes.DEFAULT_SPACE)
                .padding(top = 48.dp), // Equivalent to paddingWithAppBarHeight
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            LoginHeader()
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_SECTIONS))
            // Form
            LoginForm(
                onEmailAndPasswordSignIn = { email, password, _ ->
                    viewModel.signIn(email, password)
                },
                onNavigateToSignup = {},
                onNavigateToForgetPassword = {},
            )
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_SECTIONS))
            // Divider
            FormDivider(
                text = stringResource(R.string.login)
            )

            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_SECTIONS))
            // Footer - Social Buttons
            SocialButtons(
                onGoogleSignIn = { },
                onFacebookSignIn = { }
            )
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_SECTIONS))
        }
    }
}
