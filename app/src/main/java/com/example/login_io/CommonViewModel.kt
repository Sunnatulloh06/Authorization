package com.example.login_io

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CommonViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var name by mutableStateOf("")
    var phoneNumber by mutableStateOf("")
    var birthDay by mutableStateOf("")
    var errorMessage by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun login(onSuccess: () -> Unit) {
        isLoading = true

        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Email and password cannot be empty"
            isLoading = false
            return
        }

        if (!email.contains("@") || !email.contains(".")) {
            errorMessage = "Invalid email format"
            isLoading = false
            return
        }

        errorMessage = ""
        onSuccess()

        isLoading = false
    }


    fun signUp(onSuccess: () -> Unit) {
        isLoading = true
        if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty() && birthDay.isNotEmpty()) {
            errorMessage = ""
            onSuccess()
        } else {
            errorMessage = "Please fill all fields"
        }
        isLoading = false
    }

}
