package com.example.login_io.presentation.signup

import android.annotation.SuppressLint
import android.graphics.Paint.Align
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_io.components.CommonTextField
import com.example.login_io.CommonViewModel
import com.example.login_io.R
import com.example.login_io.components.CommonButton
import com.example.login_io.components.CommonCard
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
        var passwordVisible by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(Color(0xFF2567E8))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(Color(0xFFF5F5F5))
                    .align(Alignment.BottomCenter)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                // Кнопка назад
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .align(Alignment.Start),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
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
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(15.dp))

                CommonTextButton(
                    text1 = "Already have an account?",
                    text2 = "Login",
                    onClick = {
                        navController.navigate(Screen.Login_Screen.route)
                    },
                    horizontalAlignment = Arrangement.Center,
                    color1 = Color.White,
                    color2 = Color.White,
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(25.dp))
                // Бокс с белым фоном
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 5.dp, end = 5.dp, bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.95f)
                            .height(650.dp)
                            .background(Color.White, shape = RoundedCornerShape(14.dp))
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Fields
                            Row {
                                CommonTextField(
                                    value = view.name,
                                    onValueChange = { view.name = it },
                                    modifier = Modifier.weight(1f),
                                    keyboardType = KeyboardType.Text
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                CommonTextField(
                                    value = view.name,
                                    onValueChange = { view.name = it },
                                    modifier = Modifier.weight(1f),
                                    keyboardType = KeyboardType.Text
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))

                            CommonTextField(
                                value = view.email,
                                onValueChange = { view.email = it },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardType = KeyboardType.Email
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            CommonTextField(
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
                                value = view.phoneNumber,
                                onValueChange = { view.phoneNumber = it },
                                modifier = Modifier.fillMaxWidth(),
                                keyboardType = KeyboardType.Phone
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            CommonTextField(
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
                            Spacer(modifier = Modifier.height(30.dp))

                            // Кнопка регистрации
                            CommonButton(
                                label = "Log In",
                                onClick = {
                                    view.signUp {
                                        navController.navigate(Screen.Home_Screen.route)
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(26.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Divider(
                                    modifier = Modifier.weight(1f),
                                    color = Color.Gray,
                                    thickness = 1.dp
                                )
                                Text(
                                    text = "Or login with",
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                Divider(
                                    modifier = Modifier.weight(1f),
                                    color = Color.Gray,
                                    thickness = 1.dp
                                )
                            }
                            Spacer(modifier = Modifier.height(26.dp))

                            CommonCard(
                                text = "Continue with Google",
                                iconResId = R.drawable.google
                            )
                        }
                    }
                }
            }
        }
    }
}