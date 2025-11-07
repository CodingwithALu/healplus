package com.example.healplus.feature.authentication.signup.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.viewmodel.authviewmodel.SignupViewModel
import com.example.healplus.R
import com.example.healplus.feature.utils.route.Screen
import com.example.healplus.feature.utils.validator.ValidationUtils

@Composable
fun SignupForm(
    navController: NavController) {
    val viewModel: SignupViewModel = hiltViewModel()
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var agreeTerm by remember { mutableStateOf(false) }
    var passwordHidden by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = ValidationUtils.validateEmail(it)
            },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError != null,
            supportingText = {
                if (emailError != null) Text(emailError!!)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ValidationUtils.validatePassword(it)
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { passwordHidden = !passwordHidden }) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordHidden) R.drawable.visibility_off_24px else R.drawable.visibility_24px
                        ),
                        contentDescription = if (passwordHidden) "Show" else "Hide"
                    )
                }
            },
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) Text(passwordError!!)
            },
            enabled = true
        )
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = agreeTerm,
                onCheckedChange = { agreeTerm = it }
            )
            Text("I agree to the Terms & Conditions")
        }
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if(agreeTerm){
                    viewModel.createUser(name = userName, email = email, password = password)
                    navController.navigate("${Screen.VerifyEmail.route}/${email}")
                }
            },
            enabled = agreeTerm,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Create Account")
        }
    }
}