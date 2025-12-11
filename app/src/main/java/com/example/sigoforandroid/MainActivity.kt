package com.example.sigoforandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sigoforandroid.ui.screens.HomeScreen
import com.example.sigoforandroid.ui.screens.LoginScreen
import com.example.sigoforandroid.ui.theme.SigoForAndroidTheme // Tu tema principal
import com.example.sigoforandroid.ui.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SigoForAndroidTheme { // Asegúrate de usar el nombre de tu tema
                AppNavigation()
            }
        }
    }
}

/**
 * Gestiona la navegación de la aplicación basándose en el estado de autenticación.
 * * Si el studentData no es nulo (login exitoso), navega a HomeScreen,
 * de lo contrario, permanece en LoginScreen.
 */
@Composable
fun AppNavigation() {
    // Obtenemos una instancia del ViewModel
    val loginViewModel: LoginViewModel = viewModel()

    // Observamos el estado de los datos del estudiante
    val studentData = loginViewModel.studentData

    if (studentData != null) {
        // ✅ LOGIN EXITOSO: Navegar a Home
        // Usamos los campos actualizados del LoginResponse
        HomeScreen(
            personFullName = studentData.personFullName, // Campo corregido
            profileName = studentData.profileName         // Campo corregido
        )
    } else {
        // ❌ NO AUTENTICADO: Permanecer en Login
        LoginScreen(viewModel = loginViewModel)
    }
}