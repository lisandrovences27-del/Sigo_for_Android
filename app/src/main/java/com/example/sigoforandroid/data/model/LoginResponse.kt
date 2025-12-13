package com.example.sigoforandroid.data.model
data class LoginResponse(
    // Datos de la persona / Perfil (a mostrar en la UI)
    val personFullName: String,
    val profileName: String,
    val email: String,
    val username: String, // El usuario/matrícula
    val personId: Int,    // ID de persona (entero)

    // Datos internos de la sesión y registro (pueden ser útiles)
    val termsConditions: Boolean,
    val registerUser: String,
    val active: Boolean,
    val messageControl: String,
    val accessModule: String,
    val register: String,
    val id: Int,

    // Lista de roles (generalmente se usa para permisos)
    val roles: List<String>,

    // Token de seguridad (NO se muestra en la UI)
    val bearer: String

    // NOTA: Los campos 'controlNumber', 'curp', 'studentStatus' que usamos antes NO están
    // en este JSON, por lo que serán omitidos o tendrás que pedirlos a otra API si son necesarios.
)