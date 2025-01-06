package com.example.login_io.presentation.signup

import android.annotation.SuppressLint
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
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
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2567E8),
                            Color(0xFF1CE6DA)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            var passwordVisible by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(400.dp)
                        .height(800.dp)
                        .background(Color.White, shape = RoundedCornerShape(14.dp))
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Кнопка назад
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier.padding(top = 5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        // Title
                        Text(
                            text = "Sign Up",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        CommonTextButton(
                            text1 = "Already have an account?",
                            text2 = "Login",
                            onClick = {
                                navController.navigate(Screen.Login_Screen.route)
                            },
                            horizontalAlignment = Arrangement.Start
                        )
                        Spacer(modifier = Modifier.height(25.dp))

                        // Поля ввода
                        CommonTextField(
                            label = "Full Name",
                            value = view.name,
                            onValueChange = { view.name = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Text
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        CommonTextField(
                            label = "Email",
                            value = view.email,
                            onValueChange = { view.email = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Email
                        )
                        Spacer(modifier = Modifier.height(20.dp))

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
                        Spacer(modifier = Modifier.height(20.dp))

                        CommonTextField(
                            label = "Phone Number",
                            value = view.phoneNumber,
                            onValueChange = { view.phoneNumber = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Phone
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        CommonTextField(
                            label = "Set a password",
                            value = view.password,
                            onValueChange = { view.password = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Password,
                            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                            trailingIcon = {
                                val image =
                                    if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = image,
                                        contentDescription = "Toggle password visibility"
                                    )
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        // Кнопка регистрации
                        CommonButton(
                            label = "Register",
                            onClick = {
                                view.signUp {
                                    navController.navigate(Screen.Home_Screen.route)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}