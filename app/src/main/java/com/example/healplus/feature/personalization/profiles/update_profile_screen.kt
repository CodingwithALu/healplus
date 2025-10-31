package com.example.healplus.feature.personalization.profiles
import ChangImageProfile
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.core.model.users.UserModel
import com.example.core.viewmodel.authviewmodel.AuthViewModel
import com.example.healplus.R
import com.example.core_utils.common.styles.TSpacerStyle
import com.example.core_utils.common.widgets.TAppBar
import com.example.core_utils.common.widgets.rememberDatePickerDialog
import com.example.core_utils.common.widgets.rememberImagePickerLauncher

@Composable
fun UpdateProfileScreen(
    item: UserModel,
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
) {
    var fullName by remember { mutableStateOf(item.name) }
    val email by remember { mutableStateOf(item.email) }
    var gender by remember { mutableStateOf(item.gender) }
    val phoneNumber by remember { mutableStateOf(item.phone) }
    var urlimg by remember { mutableStateOf(item.url) }
    var birthDate by remember { mutableStateOf(item.dateBirth) }
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val datePicker = com.example.core_utils.common.widgets.rememberDatePickerDialog { date ->
        birthDate = date
    }
    val imagePickerLauncher =
        com.example.core_utils.common.widgets.rememberImagePickerLauncher { url ->
            urlimg = url
        }
    Scaffold(
            topBar = {
                com.example.core_utils.common.widgets.TAppBar(
                    title = R.string.account,
                    onClick = { navController.popBackStack() }
                )
            }
    ) { paddingValues ->
        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChangImageProfile(
                urlimg = urlimg,
                imagePickerLauncher = imagePickerLauncher,
                title = "Chọn ảnh đại diện")
            com.example.core_utils.common.styles.TSpacerStyle(16.dp)
            OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text("Họ và tên") },
                    modifier = Modifier.fillMaxWidth()
            )
            com.example.core_utils.common.styles.TSpacerStyle(16.dp)
            Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
            ) {
                Text("Giới tính")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(selected = gender == "Nam", onClick = { gender = "Nam" })
                Text("Nam")
                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(selected = gender == "Nữ", onClick = { gender = "Nữ" })
                Text("Nữ")
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                    value = email,
                    onValueChange = {},
                    label = { Text("Email: ") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = {},
                    label = { Text("Số điện thoại") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                    value = birthDate,
                    onValueChange = {},
                    label = { Text("Ngày sinh") },
                    trailingIcon = {
                        Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Chọn ngày",
                                modifier = Modifier.clickable { showDatePicker = true }
                        )
                    },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))
            Button(
                    onClick = {
                        authViewModel.updateUserAccount(
                                name = fullName,
                                email = email,
                                gender = gender,
                                phone = phoneNumber,
                                uploadedImageUrl = urlimg,
                                dateBirth = birthDate,
                                onComplete = { success, message ->
                                    if (success) {
                                        Toast.makeText(context, "Cập nhật tài khoản thành công!", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "Lỗi cập nhật: $message", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        )
                    },
                    modifier = Modifier
                        .height(50.dp)
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Icon(
                        painter = painterResource(id = R.drawable.save_24px),
                        contentDescription = "Save",
                        tint = Color.White
                )
            }
            if (showDatePicker) {
                datePicker.show()
                showDatePicker = false
            }
        }
    }
}


