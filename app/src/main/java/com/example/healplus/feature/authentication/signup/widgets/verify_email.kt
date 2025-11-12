import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.viewmodel.authviewmodel.UiEvent
import com.example.core.viewmodel.authviewmodel.VerifyEmailViewModel
import com.example.healplus.R
import com.example.healplus.feature.utils.constants.TSizes
import com.example.healplus.feature.utils.route.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyEmailScreen(
    email: String,
    navController: NavController
){
    val viewModel: VerifyEmailViewModel = hiltViewModel()
    val uiEvent by viewModel.uiEvent.collectAsState()
    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is UiEvent.RedirectToSuccessScreen -> {
                navController.navigate(Screen.SuccessScreen.route) {
                    popUpTo(Screen.VerifyEmail.route) { inclusive = true }
                }
            }
            else -> {}
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.close_small_56dp), // Replace with your clear icon resource
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = TSizes.DEFAULT_SPACE)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Images
            Image(
                painter = painterResource(id = R.drawable.sammy_line_man_receives_a_mail), // Replace with your image resource
                contentDescription = null,
                modifier = Modifier.width(100.dp) // Implement screenWidth() as needed
            )
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_SECTIONS))

            // Title & SubTitle
            Text(
                text = "Verify your email address!",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_ITEMS))
            Text(
                text = email,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_ITEMS))
            Text(
                text = "Congratulations! Your Account Awaits: Verify Your Email to Start Shopping and Experience a World of Unrivaled Deals and Personalized Offers.",
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_ITEMS))

            // Buttons
            Button(
                onClick = { viewModel.checkEmailVerificationStatus() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Continue")
            }
            Spacer(modifier = Modifier.height(TSizes.SPACE_BTW_ITEMS))

            TextButton(
                onClick = { viewModel.sendEmailVerification() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Resend Email")
            }
        }
    }
}
