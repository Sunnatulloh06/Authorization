package com.example.login_io.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
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
        // Градиентный фон
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
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Логотип
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.top_icon),
                            contentDescription = "Main Icon",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.size(25.dp),
                            alignment = Alignment.Center,
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(Color.White)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            "Logoipsum",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                // Белый блок с формой
                Box(
                    modifier = Modifier
                        .width(400.dp)
                        .height(680.dp)
                        .background(Color.White, shape = RoundedCornerShape(14.dp))
                        .padding(start = 16.dp, end = 16.dp, top = 40.dp)
                        .align(Alignment.CenterHorizontally),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Заголовок
                        Text(
                            "Login",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 45.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        CommonTextButton(
                            text1 = "Don't have an account?",
                            text2 = "SignUp",
                            onClick = {
                                navController.navigate(Screen.Signup_Screen.route)
                            },
                            horizontalAlignment = Arrangement.Center
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        // Поле Email
                        CommonTextField(
                            label = "Email",
                            value = view.email,
                            onValueChange = { view.email = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardType = KeyboardType.Email
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        // Поле Password
                        var passwordVisible by remember { mutableStateOf(false) }
                        CommonTextField(
                            label = "Password",
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

                        // Remember Me и Forgot Password
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
                                verticalAlignment = Alignment.CenterVertically
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
                                    fontSize = 16.sp
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

                        // Кнопка Login
                        CommonButton(
                            label = "Login",
                            onClick = {
                                view.login {
                                    navController.navigate(Screen.Home_Screen.route)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Разделитель OR
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Divider(
                                modifier = Modifier.weight(1f),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                            Text(
                                text = "OR",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Divider(
                                modifier = Modifier.weight(1f),
                                color = Color.Gray,
                                thickness = 1.dp
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Кнопки социальных сетей
                        CommonCard(text = "Continue with Google", iconResId = R.drawable.google)
                        Spacer(modifier = Modifier.height(10.dp))
                        CommonCard(text = "Continue with Facebook", iconResId = R.drawable.facebook)
                    }
                }
            }
        }
    }
}
