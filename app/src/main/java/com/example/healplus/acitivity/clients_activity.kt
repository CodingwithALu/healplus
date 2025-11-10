package com.example.healplus.acitivity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.healplus.navigation.MyAppNavigation
import com.example.healplus.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientApp()
        }
    }
}

@Composable
fun ClientApp() {
    AppTheme {
        val navController = rememberNavController()
        MyAppNavigation(
            navController = navController
        )
    }
}


