package com.example.sigoforandroid.data.model

data class Quarter(
    val id: Int,
    val userId: Int,
    val nombre: String,
    val periodo: String,
    val fechaInicio: String,
    val fechaFin: String? = null,
    val carrera: String,
    val grupo: String,
    val tutor: String,
    val promedio: Float? = null,
    val estado: String // "activo" o "finalizado"
)