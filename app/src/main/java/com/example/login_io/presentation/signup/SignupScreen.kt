package com.example.login_io.presentation.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_io.components.CommonTextField
import com.example.login_io.CommonViewModel
import com.example.login_io.components.CommonButton
import com.example.login_io.components.CommonTextButton
import com.example.login_io.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignupScreen(navController: NavController, view: CommonViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }

    if (view.errorMessage.isNotEmpty()) {
        LaunchedEffect(view.errorMessage) {
            snackbarHostState.showSnackbar(
                message = view.errorMessage,
                duration = SnackbarDuration.Short
            )
            view.errorMessage = ""
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) {
        var passwordVisible by remember { mutableStateOf(false) }
//        Logo
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Create an account to continue!")
            }
            Spacer(modifier = Modifier.height(30.dp))

//            Start of Fields
//            Full name
            CommonTextField(
                label = "Full Name",
                value = view.name,
                onValueChange = {view.name = it},
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Text
            )
            Spacer(modifier = Modifier.height(15.dp))

//            Email
            CommonTextField(
                label = "Email",
                value = view.email,
                onValueChange = {view.email = it},
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(15.dp))

//            Date of birth
            CommonTextField(
                label = "Date of birth",
                value = view.birthDay,
                onValueChange = {view.birthDay =  it},
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Calendar",
                        modifier = Modifier.clickable {  }
                    )
                }
            )
            Spacer(modifier = Modifier.height(15.dp))

//            Phone Number
            CommonTextField(
                label = "Phone Number",
                value = view.phoneNumber,
                onValueChange = {view.phoneNumber = it},
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Phone,
            )
            Spacer(modifier = Modifier.height(15.dp))

//            Setting a Password
            CommonTextField(
                label = "Set a password",
                value = view.password,
                onValueChange = {view.password = it},
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = image,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(26.dp))

//            Register Button
            CommonButton(
                label = "Register",
                onClick = {
                    view.signUp {
                        navController.navigate(Screen.Home_Screen)
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            CommonTextButton(
                text1 = "Already have an account?",
                text2 = "Login",
                onClick = {
                    navController.navigate(Screen.Login_Screen.route)
                }
            )
        }
    }
}
