package com.example.sigoforandroid.data.repository

import com.example.sigoforandroid.data.model.User

object UserRepository {
    private val users = listOf(
        User(
            id = 1,
            matricula = "20230001",
            username = "estudiante1",
            password = "123456",
            nombres = "Juan",
            primerApellido = "Pérez",
            segundoApellido = "García",
            fechaNacimiento = "15/05/2000",
            sexo = "M",
            curp = "PEGJ000515HDFRRRA1",
            numeroSeguridadSocial = "12345678901",
            telefonoPersonal = "5512345678",
            emailPersonal = "juan.perez@email.com",
            carrera = "Ingeniería en Sistemas",
            semestre = 4
        ),
        User(
            id = 2,
            matricula = "20230002",
            username = "estudiante2",
            password = "abcdef",
            nombres = "María",
            primerApellido = "López",
            segundoApellido = "Rodríguez",
            fechaNacimiento = "22/08/2001",
            sexo = "F",
            curp = "LORM010822MDFPDRA2",
            numeroSeguridadSocial = "23456789012",
            telefonoPersonal = "5523456789",
            emailPersonal = "maria.lopez@email.com",
            carrera = "Ingeniería Industrial",
            semestre = 3
        ),
        User(
            id = 3,
            matricula = "20230003",
            username = "estudiante3",
            password = "qwerty",
            nombres = "Carlos",
            primerApellido = "Martínez",
            segundoApellido = "Sánchez",
            fechaNacimiento = "10/12/1999",
            sexo = "M",
            curp = "MASC991210HDFRNRA3",
            numeroSeguridadSocial = "34567890123",
            telefonoPersonal = "5534567890",
            emailPersonal = "carlos.martinez@email.com",
            carrera = "Administración",
            semestre = 5
        )
    )

    fun authenticate(matricula: String, password: String): User? {
        return users.find { it.matricula == matricula && it.password == password }
    }
}