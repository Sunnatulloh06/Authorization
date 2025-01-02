package com.example.login_io.presentation.login

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
import com.example.login_io.components.CommonTextButton
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
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                "Forgot password?",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)
                    .clickable { }
            )
            Spacer(modifier = Modifier.height(20.dp))

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
            CommonCard(
                text = "Continue with Google",
                iconResId = R.drawable.google
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Button Continue with Facebook
            CommonCard(
                text = "Continue with Facebook",
                iconResId = R.drawable.facebook
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Registration Button
            CommonTextButton (
                text1 = "Don't have an account?",
                text2 = "SignUp",
                onClick = {
                    navController.navigate(Screen.Signup_Screen.route)
                }
            )
        }
    }
}
