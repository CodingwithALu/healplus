package com.example.healplus.feature.personalization.profiles

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.core.model.users.UserModel
import com.example.healplus.R
import com.example.healplus.feature.common.widgets.TAppBar
import com.example.healplus.feature.common.widgets.TAvatarImage
import com.example.healplus.feature.personalization.profiles.widgets.TEditButtonApp
import com.example.healplus.feature.personalization.profiles.widgets.TRowItemProfile
import com.google.gson.Gson

@Composable
fun ProfileScreen(user: UserModel, navController: NavController) {
    Scaffold(
        topBar = {
            TAppBar(
                title = R.string.information_acconut,
                onClick = { navController.popBackStack() },
            )
        }

    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Image Profile
                    TAvatarImage(
                        showChangeImage = false,
                        uploadedImageUrls = user.url ?: "", // <-- null -> ""
                    )
                }
            }
            item {
                TRowItemProfile(label = "Họ và tên", value = user.name ?: "")
                TRowItemProfile(label = "Giới tính", value = user.gender ?: "")
                TRowItemProfile(label = "Ngày sinh", value = user.dateBirth ?: "")
            }
            item {
                TEditButtonApp(onClick = {
                    navController.navigate("editProfile/${Uri.encode(Gson().toJson(user))}")
                })
            }
        }

    }

}