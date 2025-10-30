//package com.example.core_utils.common.widgets.login_signup// SocialButtons.kt
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.example.core_utils.utils.constants.TSizes
//import com.example.core_utils.R
//@Composable
//fun SocialButtons(
//    onGoogleSignIn: () -> Unit,
//    onFacebookSignIn: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        // Google Sign In Button
//        IconButton(
//            onClick = onGoogleSignIn,
//            modifier = Modifier
//                .size(TSizes.APP_BAR_HEIGHT)
//                .clip(CircleShape)
//        ) {
//            Card(
//                modifier = Modifier.fillMaxSize(),
//                colors = CardDefaults.cardColors(containerColor = Color.White),
//                elevation = CardDefaults.cardElevation(defaultElevation = TSizes.XS)
//            ) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.google_icon),
//                        contentDescription = "Google Sign In",
//                        modifier = Modifier.size(24.dp),
//                        tint = Color.Unspecified
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.width(TSizes.SPACE_BTW_ITEMS))
//
//        // Facebook Sign In Button
//        IconButton(
//            onClick = onFacebookSignIn,
//            modifier = Modifier
//                .size(TSizes.APP_BAR_HEIGHT)
//                .clip(CircleShape)
//        ) {
//            Card(
//                modifier = Modifier.fillMaxSize(),
//                colors = CardDefaults.cardColors(containerColor = Color.White),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//            ) {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.facebook_icon),
//                        contentDescription = "Facebook Sign In",
//                        modifier = Modifier.size(TSizes.LG),
//                        tint = Color.Unspecified
//                    )
//                }
//            }
//        }
//    }
//}