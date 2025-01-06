package com.example.login_io.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_io.CommonViewModel
import com.example.login_io.R
import com.example.login_io.components.CommonButton
import com.example.login_io.components.CommonCard
import com.example.login_io.components.CommonTextButton
import com.example.login_io.components.CommonTextField
import com.example.login_io.components.CommonTitle
import com.example.login_io.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController, view: CommonViewModel) {
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
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFF2567E8))
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFFF5F5F5))
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                // Логотип
                Column(
                    modifier = Modifier
                        .padding(16.dp, bottom = 20.dp)
                        .padding(end = 15.dp)
                        .align(Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.top_icon),
                        contentDescription = "Main Icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(25.dp),
                        alignment = Alignment.Center,
                        colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    CommonTitle(
                        text = "Sign in to your"
                    )
                    CommonTitle(
                        text = "Acccount"
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        "Enter your email and password to log in",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                // Белый бокс
                Box(
                    modifier = Modifier
                        .width(382.dp)
                        .height(490.dp)
                        .background(Color.White, shape = RoundedCornerShape(14.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        CommonCard(
                            text = "Continue with Google",
                            iconResId = R.drawable.google
                        )
                        Spacer(modifier = Modifier.height(24.dp))

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
                        Spacer(modifier = Modifier.height(20.dp))

                        // Поля ввода
                        CommonTextField(
                            value = view.email,
                            onValueChange = { view.email = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Email
                        )
                        Spacer(modifier = Modifier.height(18.dp))


                        var passwordVisible by remember { mutableStateOf(false) }
                        CommonTextField(
                            value = view.password,
                            onValueChange = { view.password = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Password,
                            visualTransformation = if (!passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
                            trailingIcon = {
                                val image =
                                    if (!passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = image,
                                        contentDescription = "Toggle password visibility"
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        var rememberMe by remember { mutableStateOf(false) }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.padding(start = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Checkbox(
                                    checked = rememberMe,
                                    onCheckedChange = { rememberMe = it },
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Remember me",
                                    color = Color.Gray,
                                    fontSize = 16.sp,
                                    modifier = Modifier.clickable { rememberMe = !rememberMe }
                                )
                            }

                            Text(
                                text = "Forgot password?",
                                color = Color.Blue,
                                fontSize = 16.sp,
                                modifier = Modifier.clickable { }
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        CommonButton(
                            label = "Log In",
                            onClick = {
                                view.login {
                                    navController.navigate(Screen.Home_Screen.route)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(28.dp))
                        CommonTextButton(
                            text1 = "Don't have an account?",
                            text2 = "SignUp",
                            onClick = {
                                navController.navigate(Screen.Signup_Screen.route)
                            },
                            horizontalAlignment = Arrangement.Center,
                            color1 = Color.Black,
                            color2 = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}