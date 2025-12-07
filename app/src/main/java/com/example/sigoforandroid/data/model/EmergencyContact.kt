package com.example.sigoforandroid.data.model

data class EmergencyContact(
    val id: Int,
    val userId: Int,
    val nombreCompleto: String,
    val parentesco: String,
    val telefonoCelular: String,
    val telefonoCasa: String,
    val telefonoTrabajo: String = "", // Nuevo campo
    val extension: String = "" // Nuevo campo
)