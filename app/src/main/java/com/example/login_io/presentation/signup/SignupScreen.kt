package com.example.login_io.presentation.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_io.components.CommonTextField
import com.example.login_io.CommonViewModel
import com.example.login_io.R
import com.example.login_io.components.CommonButton
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Column(
                modifier = Modifier
                    .padding(end = 16.dp, top = 60.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.top_icon),
                    contentDescription = "Main Icon",
                    modifier = Modifier
                        .size(36.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    "Get Started now",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        "Create an account",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.Signup_Screen.route)
                            },
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("or", fontSize = 18.sp)
                    Text(
                        "log in",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(Screen.Login_Screen.route)
                            },
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("to explore us", fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "about our app",
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .padding(top = 275.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(
                        color = Color(0xFFF5F6F9),
                        shape = RoundedCornerShape(10.dp)
                    ),
                contentAlignment = Alignment.Center
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Log in",
                            fontSize = 18.sp,
                            color = Color(0xFF7D7D91)
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(vertical = 8.dp,)
                    ) {
                        Text(
                            "Sign up",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(26.dp))

            // Fields
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                CommonTextField(
                    label = "First Name",
                    value = view.name,
                    onValueChange = { view.name = it },
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Text
                )
                Spacer(modifier = Modifier.width(10.dp))
                CommonTextField(
                    label = "Last Name",
                    value = view.name,
                    onValueChange = { view.name = it },
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Text
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                label = "Email",
                value = view.email,
                onValueChange = { view.email = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                label = "Date of birth",
                value = view.birthDay,
                onValueChange = { view.birthDay = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Number,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Calendar",
                        modifier = Modifier.clickable { }
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                label = "Phone number",
                value = view.phoneNumber,
                onValueChange = { view.phoneNumber = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Phone
            )
            Spacer(modifier = Modifier.height(16.dp))

            CommonTextField(
                label = "Set a password",
                value = view.password,
                onValueChange = { view.password = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Password,
                visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                trailingIcon = {
                    val image =
                        if (!passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(onClick = {
                        passwordVisible = !passwordVisible
                    }) {
                        Icon(
                            imageVector = image,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(27.dp))

            // Registration Button
            CommonButton(
                label = "Register",
                onClick = {
                    view.signUp {
                        navController.navigate(Screen.Home_Screen.route)
                    }
                }
            )
            Spacer(modifier = Modifier.height(26.dp))
        }
    }
}
