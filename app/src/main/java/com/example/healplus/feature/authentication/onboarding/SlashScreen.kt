package com.example.healplus.feature.authentication.onboarding

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.Scaffold
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.core.viewmodel.AuthViewModel1
import com.example.healplus.R
import com.example.healplus.feature.utils.route.Screen
import kotlinx.coroutines.delay

@Composable
fun LottieLoadingAnimation(navController: NavController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.main_screen))
    val authViewModel: AuthViewModel1 = hiltViewModel()
    val appLogin = authViewModel.appLogin.collectAsState()
    LaunchedEffect(Unit) {
        delay(2000)
        Log.d("isLogin", "Check: ${appLogin.value}")
        if (appLogin.value) {
            navController.navigate(Screen.Home.route) {
                popUpTo(0) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(0) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(150.dp)
            )
        }
    }
}