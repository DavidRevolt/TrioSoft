package com.davidrevolt.feature.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.davidrevolt.core.designsystem.icons.AppIcons

@Composable
fun SignUpScreen(onSuccessRegister: () -> Unit, viewModel: SignUpViewModel = hiltViewModel()) {

    val onSignUpClick = viewModel::onSignUpClick
    val authState = viewModel.authState.collectAsStateWithLifecycle()

    if(authState.value)
        onSuccessRegister()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(16.dp, 4.dp)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(modifier = Modifier.padding(5.dp)){
            Column(
                modifier = Modifier.padding(top=25.dp, bottom = 5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedTextField(
                modifier = fieldModifier,
                singleLine = true,
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email") },
                leadingIcon = { Icon(imageVector = AppIcons.Email, contentDescription = "Email") }
            )
            OutlinedTextField(
                modifier = fieldModifier,
                singleLine = true,
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                label = { Text("Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = password != repeatPassword,
                leadingIcon = {
                    Icon(
                        imageVector = AppIcons.Password,
                        contentDescription = "Password"
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
            )
            OutlinedTextField(
                modifier = fieldModifier,
                singleLine = true,
                value = repeatPassword,
                onValueChange = { repeatPassword = it },
                placeholder = { Text("Repeat Password") },
                label = { Text("Repeat Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = password != repeatPassword,
                leadingIcon = {
                    Icon(
                        imageVector = AppIcons.Password,
                        contentDescription = "Repeat Password"
                    )
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = { onSignUpClick(email, password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.error,
                    contentColor = White,
                    disabledContentColor = White
                ),
                enabled = (password == repeatPassword) && password.isNotEmpty() && email.isNotEmpty()
            ) {
                Text(text = "Sign Up!", fontSize = 16.sp)
            }
        }}
    }
}