package com.example.sigoforandroid.data.model

data class User(
    val id: Int,
    val matricula: String,
    val username: String,
    val password: String,
    val nombres: String,
    val primerApellido: String,
    val segundoApellido: String,
    val fechaNacimiento: String,
    val sexo: String,
    val curp: String,
    val numeroSeguridadSocial: String,
    val telefonoPersonal: String,
    val emailPersonal: String,
    val carrera: String,
    val semestre: Int
)