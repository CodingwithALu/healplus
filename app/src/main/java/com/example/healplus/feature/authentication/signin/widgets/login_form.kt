package com.example.healplus.feature.authentication.signin.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.healplus.R
import com.example.core_utils.utils.constants.TSizes
import com.example.core_utils.utils.validator.ValidationUtils

@Composable
fun LoginForm(
    onEmailAndPasswordSignIn: (String, String, Boolean) -> Unit,
    onNavigateToSignup: () -> Unit,
    onNavigateToForgetPassword: () -> Unit,
    isLoading: Boolean = false
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }
    var hidePassword by remember { mutableStateOf(true) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Email TextField
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = ValidationUtils.validateEmail(it)
            },
            label = { Text(stringResource(R.string.email)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError != null,
            supportingText = {
                if (emailError != null) Text(emailError!!)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(TSizes.MD))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ValidationUtils.validatePassword(it)
            },
            label = { Text(stringResource(R.string.password)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(onClick = { hidePassword = !hidePassword }) {
                    Icon(
                        painter = painterResource(
                            id = if (hidePassword) R.drawable.visibility_off_24px else R.drawable.visibility_24px
                        ),
                        contentDescription = if (hidePassword) "Show password" else "Hide password"
                    )
                }
            },
            visualTransformation = if (hidePassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = passwordError != null,
            supportingText = {
                if (passwordError != null) Text(passwordError!!)
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = true
        )

        Spacer(modifier = Modifier.height(TSizes.SM))

        // Remember Me & Forget Password Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it }
                )
                Text(
                    text = "Remember me",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            TextButton(
                onClick = onNavigateToForgetPassword
            ) {
                Text("Forget Password")
            }
        }

        Spacer(modifier = Modifier.height(TSizes.MD))

        // Sign In Button
        Button(
            onClick = {
                emailError = ValidationUtils.validateEmail(email)
                passwordError = ValidationUtils.validatePassword(password)
                if (emailError == null && passwordError == null) {
                    onEmailAndPasswordSignIn(email, password, rememberMe)
                }
            },
            enabled = !isLoading,
            modifier = Modifier
                .fillMaxWidth()
                .height(TSizes.APP_BAR_HEIGHT)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = stringResource(R.string.login),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(TSizes.MD))

        // Create Account Button
        OutlinedButton(
            onClick = onNavigateToSignup,
            modifier = Modifier
                .fillMaxWidth()
                .height(TSizes.APP_BAR_HEIGHT)
        ) {
            Text(
                text = stringResource(R.string.have_an_account),
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}