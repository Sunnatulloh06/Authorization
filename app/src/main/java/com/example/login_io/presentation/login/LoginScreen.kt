package com.example.login_io.presentation.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.login_io.components.CommonCard
import com.example.login_io.navigation.Screen

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

//        Logo and Title
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            contentAlignment = Alignment.TopCenter
        ){
            Column(
                modifier = Modifier
                    .padding(end = 16.dp, top = 80.dp),
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

//        Блок начиная с полей ввода
        Column(
            modifier = Modifier
                .padding(20.dp)
                .padding(top = 240.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                            "Log In",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Sign Up",
                            fontSize = 18.sp,
                            color = Color(0xFF7D7D91)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            // TextField Email
            CommonTextField(
                label = "Email",
                value = view.email,
                onValueChange = {view.email = it},
                modifier = Modifier.fillMaxWidth(),
                keyboardType = KeyboardType.Email,
            )
            Spacer(modifier = Modifier.height(15.dp))

            //TextField Password
            CommonTextField(
                label = "Password",
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
            Spacer(modifier = Modifier.height(14.dp))

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
            Spacer(modifier = Modifier.height(27.dp))

            // Button Login
            CommonButton(
                label = "Login",
                onClick = {
                    view.login {
                        navController.navigate(Screen.Home_Screen)
                    }
                }
            )

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
                    text = "Or log in with",
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                CommonCard(
                    text = "Google",
                    iconResId = R.drawable.google
                )
                Spacer(modifier = Modifier.width(15.dp))

                CommonCard(
                    text = "Facebook",
                    iconResId = R.drawable.facebook
                )
                Spacer(modifier = Modifier.width(15.dp))

                CommonCard(
                    text = "Apple phone",
                    iconResId = R.drawable.apple
                )
                Spacer(modifier = Modifier.width(15.dp))

                CommonCard(
                    text = "Just Phone",
                    iconResId = R.drawable.phone
                )

            }

            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}