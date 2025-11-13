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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.healplus.R
import com.example.healplus.feature.authentication.signin.widgets.LoginForm
import com.example.healplus.feature.authentication.signin.widgets.LoginHeader
import com.example.healplus.feature.common.widgets.login_signup.SocialButtons
import com.example.healplus.feature.utils.constants.TSizes

@Composable
fun SignInScreen(
    navController: NavController
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = TSizes.DEFAULT_SPACE/2)
                .padding(top = TSizes.DEFAULT_SPACE), // Equivalent to paddingWithAppBarHeight
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            LoginHeader()
            Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE))
            // Form
            LoginForm(navController = navController)
            Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE))
            // Divider
            FormDivider(
                text = stringResource(R.string.login)
            )

            Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE))
            // Footer - Social Buttons
            SocialButtons(navController)
            Spacer(modifier = Modifier.height(TSizes.DEFAULT_SPACE))
        }
    }
}
