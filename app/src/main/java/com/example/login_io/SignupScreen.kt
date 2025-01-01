package com.example.login_io

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

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
                    fontSize = 40.sp,
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
                onValueChange = {view.name = it}
            )

//            Email
            CommonTextField(
                label = "Email",
                value = view.email,
                onValueChange = {view.email = it}
            )

//            Date of birth
            CommonTextField(
                label = "Date of birth",
                value = view.birthDay,
                onValueChange = {view.birthDay =  it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

//            Phone Number
            CommonTextField(
                label = "Phone Number",
                value = view.phoneNumber,
                onValueChange = {view.phoneNumber = it},
            )

//            Setting a Password
            CommonTextField(
                label = "Set a password",
                value = view.password,
                onValueChange = {view.password = it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = {passwordVisible = !passwordVisible}
            )

            Button(
                onClick = {
                    view.signUp {
                        navController.navigate(Screen.Home_Screen.route)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1D61E7),
                    contentColor = Color.White
                )
            ) {
                Text("Register", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Already have an account?")
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Login_Screen.route)
                    }
                ) {
                    Text("Login")
                }
            }
        }
    }
}
