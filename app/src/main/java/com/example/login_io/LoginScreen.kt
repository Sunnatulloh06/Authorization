package com.example.login_io

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lint.kotlin.metadata.Visibility
import androidx.navigation.NavController

@SuppressLint("RememberReturnType", "UnusedMaterial3ScaffoldPaddingParameter")
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
    ) {
        var passwordVisible by remember { mutableStateOf(false) }
//        Logo
        Row(
            modifier = Modifier.padding(16.dp, top = 90.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.top_icon),
                contentDescription = "Main Icon",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Logoipsum", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
//        Title
        Column(
            modifier = Modifier
                .padding(16.dp, top = 160.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Sign in to your\nAccount",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Enter your email and password to log in",
                fontSize = 18.sp
            )
        }
//        Блок начиная с полей ввода
        Column(
            modifier = Modifier
                .padding(16.dp)
                .padding(top = 240.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // TextField Email
            CommonTextField(
                label = "Email",
                value = view.email,
                onValueChange = {view.email = it},
            )
            Spacer(modifier = Modifier.height(15.dp))

            //TextField Password
            CommonTextField(
                label = "Password",
                value = view.password,
                onValueChange = {view.password = it},
                isPassword = true,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = {passwordVisible = !passwordVisible}
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "Forgot password?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
                    .clickable { }
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Button Login
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D61E7)),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    view.login {
                        navController.navigate(Screen.Home_Screen.route)
                    }
                }
            ) {
                Text("Login", fontSize = 20.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Line with Text "OR"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                Text(
                    text = "OR",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Divider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    color = Color.Gray,
                    thickness = 1.dp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Button "Continue with Google"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(vertical = 4.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                    .clickable { },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = "Google Icon",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Continue with Google",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Button Continue with Facebook
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(vertical = 4.dp)
                    .shadow(elevation = 4.dp, shape = RoundedCornerShape(12.dp))
                    .clickable { },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.facebook),
                            contentDescription = "Google Icon",
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Continue with Facebook",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Registration Button
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Don't have an account?")
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Signup_Screen.route)
                    }
                ) {
                    Text("SignUp")
                }
            }
        }
    }
}
