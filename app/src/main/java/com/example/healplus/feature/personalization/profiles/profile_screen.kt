package com.example.healplus.feature.personalization.profiles

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.core.viewmodel.authviewmodel.AuthViewModel
import com.example.healplus.R
import com.example.healplus.common.widgets.TAppBar
import com.example.healplus.common.widgets.TAvatarImage
import com.example.healplus.feature.personalization.profiles.widgets.TEditButtonApp
import com.example.healplus.feature.personalization.profiles.widgets.TRowItemProfile
import com.google.gson.Gson

@Composable
fun ProfileScreen(viewModel: AuthViewModel, navController: NavController) {
    val user by viewModel.user.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()
    }
    Scaffold(
        topBar = {
            TAppBar(
                title = R.string.information_acconut,
                onClick = { navController.popBackStack() },
            )
        }

    ) {paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)) {
            user?.let { userData ->
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    // Image Profile
                    TAvatarImage(
                        showChangeImage = false,
                        uploadedImageUrls = userData.url,
                    )
                }
                TRowItemProfile(label = "Họ và tên", value = userData.name)
                TRowItemProfile(label = "Số điện thoại", value = userData.phone)
                TRowItemProfile(label = "Giới tính", value = userData.gender)
                TRowItemProfile(label = "Ngày sinh", value = userData.dateBirth)
                Spacer(modifier = Modifier.weight(1f))
                TEditButtonApp(onClick = {
                    navController.navigate("editProfile/${Uri.encode(Gson().toJson(userData))}")
                })
            } ?: Text(text = "Không có dữ liệu người dùng")
        }
    }
}