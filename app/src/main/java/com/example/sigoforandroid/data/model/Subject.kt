package com.example.sigoforandroid.data.model

data class Subject(
    val id: Int,
    val quarterId: Int,
    val nombre: String,
    val clave: String,
    val creditos: Int,
    val calificacion: Float? = null,
    val profesor: String,
    val horario: String? = null,
    val aula: String? = null,
    val progreso: String? = null,
    val evaluacion: String? = null,
    val desempeno: String? = null
)