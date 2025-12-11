package com.example.sigoforandroid.data.model

data class LoginResponse(
    // Datos de la persona que se mostrarán en la pantalla principal:
    val personFullName: String, // Nombre completo (ej: Angel Jair)
    val profileName: String,    // Perfil/Carrera (ej: Alumno, Ingenieria...)

    // Datos de la sesión (importantes para navegación/peticiones futuras):
    val bearer: String,         // El token de seguridad

    // Otros datos de control que necesitas para el modelo:
    val termsConditions: Boolean,
    val registerUser: String,
    val active: Boolean,
    val messageControl: String,
    val accessModule: String,
    val personId: Int,
    val register: String,
    val email: String,
    val id: Int,
    val username: String,
    val password: String,
    val roles: List<String>
)