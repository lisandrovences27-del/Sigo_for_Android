package com.example.sigoforandroid.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sigoforandroid.data.model.LoginRequest
import com.example.sigoforandroid.data.model.LoginResponse
import com.example.sigoforandroid.data.network.RetrofitClient
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    // Estados de los campos de texto
    var username by mutableStateOf("")
    var password by mutableStateOf("")

    // Estados de la UI para el Login
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Datos del alumno para la navegación
    var studentData by mutableStateOf<LoginResponse?>(null)

    fun performLogin() {
        if (username.isBlank() || password.isBlank()) {
            errorMessage = "Llena todos los campos"
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                val request = LoginRequest(username, password)
                val response = RetrofitClient.apiService.login(request)

                if (response.isSuccessful && response.body() != null) {
                    studentData = response.body() // ¡Éxito!
                } else {
                    errorMessage = "Error: Credenciales incorrectas o servidor falló"
                }
            } catch (e: Exception) {
                errorMessage = "Error de conexión: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
    fun logout() {
        studentData = null
        username = ""
        password = ""
        errorMessage = null
    }
}