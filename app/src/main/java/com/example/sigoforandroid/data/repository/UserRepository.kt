package com.example.sigoforandroid.data.repository

import com.example.sigoforandroid.data.model.*

object UserRepository {
    // Datos de usuarios
    private val users = listOf(
        User(
            id = 1,
            matricula = "20230001",
            username = "estudiante",
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

    // Contactos de emergencia
    private val emergencyContacts = listOf(
        EmergencyContact(
            id = 1,
            userId = 1,
            nombreCompleto = "María García López",
            parentesco = "Madre",
            telefonoCelular = "5511223344",
            telefonoCasa = "5555667788",
            telefonoTrabajo = "5555778899", // Nuevo
            extension = "123" // Nuevo
        ),
        EmergencyContact(
            id = 2,
            userId = 2,
            nombreCompleto = "José López Ramírez",
            parentesco = "Padre",
            telefonoCelular = "5522334455",
            telefonoCasa = "5566778899",
            telefonoTrabajo = "", // Vacío
            extension = "" // Vacío
        ),
        EmergencyContact(
            id = 3,
            userId = 3,
            nombreCompleto = "Ana Sánchez Fernández",
            parentesco = "Madre",
            telefonoCelular = "5533445566",
            telefonoCasa = "5577889900",
            telefonoTrabajo = "5577990011",
            extension = "456"
        )
    )

    // Información institucional
    private val institutionalInfos = listOf(
        InstitutionalInfo(
            id = 1,
            userId = 1,
            emailInstitucional = "20230001@utm.edu.mx",
            passwordEmail = "password123",
            usuarioELibro = "20230001",
            passwordELibro = "libro123"
        ),
        InstitutionalInfo(
            id = 2,
            userId = 2,
            emailInstitucional = "20230002@utm.edu.mx",
            passwordEmail = "password456",
            usuarioELibro = "20230002",
            passwordELibro = "libro456"
        ),
        InstitutionalInfo(
            id = 3,
            userId = 3,
            emailInstitucional = "20230003@utm.edu.mx",
            passwordEmail = "password789",
            usuarioELibro = "20230003",
            passwordELibro = "libro789"
        )
    )

    // Cuatrimestres
    private val quarters = listOf(
        // Usuario 1
        Quarter(
            id = 1,
            userId = 1,
            nombre = "1er Cuatrimestre",
            periodo = "Sep-Dic 2024",
            fechaInicio = "01/09/2024",
            fechaFin = "15/12/2024",
            carrera = "Ingeniería en Sistemas",
            grupo = "IS-2024-1",
            tutor = "Dr. Rodríguez",
            promedio = 8.5f,
            estado = "finalizado"
        ),
        Quarter(
            id = 2,
            userId = 1,
            nombre = "2do Cuatrimestre",
            periodo = "Ene-Abr 2025",
            fechaInicio = "15/01/2025",
            fechaFin = "30/04/2025",
            carrera = "Ingeniería en Sistemas",
            grupo = "IS-2024-2",
            tutor = "Dra. Martínez",
            promedio = 9.0f,
            estado = "finalizado"
        ),
        Quarter(
            id = 3,
            userId = 1,
            nombre = "3er Cuatrimestre",
            periodo = "May-Ago 2025",
            fechaInicio = "05/05/2025",
            fechaFin = "20/08/2025",
            carrera = "Ingeniería en Sistemas",
            grupo = "IS-2024-3",
            tutor = "Ing. López",
            promedio = 8.7f,
            estado = "finalizado"
        ),
        Quarter(
            id = 4,
            userId = 1,
            nombre = "4to Cuatrimestre",
            periodo = "Sep-Dic 2025",
            fechaInicio = "01/09/2025",
            carrera = "Ingeniería en Sistemas",
            grupo = "IS-2024-4",
            tutor = "Mtro. Hernández",
            estado = "activo"
        ),
        // Usuario 2
        Quarter(
            id = 5,
            userId = 2,
            nombre = "1er Cuatrimestre",
            periodo = "Sep-Dic 2024",
            fechaInicio = "01/09/2024",
            fechaFin = "15/12/2024",
            carrera = "Ingeniería Industrial",
            grupo = "II-2024-1",
            tutor = "Ing. Ramírez",
            promedio = 8.8f,
            estado = "finalizado"
        ),
        Quarter(
            id = 6,
            userId = 2,
            nombre = "2do Cuatrimestre",
            periodo = "Ene-Abr 2025",
            fechaInicio = "15/01/2025",
            fechaFin = "30/04/2025",
            carrera = "Ingeniería Industrial",
            grupo = "II-2024-2",
            tutor = "Dra. Silva",
            promedio = 9.2f,
            estado = "finalizado"
        ),
        Quarter(
            id = 7,
            userId = 2,
            nombre = "3er Cuatrimestre",
            periodo = "May-Ago 2025",
            fechaInicio = "05/05/2025",
            fechaFin = "20/08/2025",
            carrera = "Ingeniería Industrial",
            grupo = "II-2024-3",
            tutor = "Mtro. Vargas",
            promedio = 8.9f,
            estado = "finalizado"
        ),
        Quarter(
            id = 8,
            userId = 2,
            nombre = "4to Cuatrimestre",
            periodo = "Sep-Dic 2025",
            fechaInicio = "01/09/2025",
            carrera = "Ingeniería Industrial",
            grupo = "II-2024-4",
            tutor = "Dr. Mendoza",
            estado = "activo"
        ),
        // Usuario 3
        Quarter(
            id = 9,
            userId = 3,
            nombre = "1er Cuatrimestre",
            periodo = "Sep-Dic 2024",
            fechaInicio = "01/09/2024",
            fechaFin = "15/12/2024",
            carrera = "Administración",
            grupo = "AD-2024-1",
            tutor = "Lic. Ortega",
            promedio = 9.5f,
            estado = "finalizado"
        ),
        Quarter(
            id = 10,
            userId = 3,
            nombre = "2do Cuatrimestre",
            periodo = "Ene-Abr 2025",
            fechaInicio = "15/01/2025",
            fechaFin = "30/04/2025",
            carrera = "Administración",
            grupo = "AD-2024-2",
            tutor = "Mtro. Ríos",
            promedio = 9.7f,
            estado = "finalizado"
        ),
        Quarter(
            id = 11,
            userId = 3,
            nombre = "3er Cuatrimestre",
            periodo = "May-Ago 2025",
            fechaInicio = "05/05/2025",
            fechaFin = "20/08/2025",
            carrera = "Administración",
            grupo = "AD-2024-3",
            tutor = "Dra. Castro",
            promedio = 9.3f,
            estado = "finalizado"
        ),
        Quarter(
            id = 12,
            userId = 3,
            nombre = "4to Cuatrimestre",
            periodo = "Sep-Dic 2025",
            fechaInicio = "01/09/2025",
            carrera = "Administración",
            grupo = "AD-2024-4",
            tutor = "Lic. Romero",
            estado = "activo"
        )
    )

    // Materias
    private val subjects = listOf(
        // Usuario 1 - Cuatrimestre 4 (activo)
        Subject(
            id = 1,
            quarterId = 4,
            nombre = "Programación Avanzada",
            clave = "IS-401",
            creditos = 5,
            profesor = "Dr. Carlos Ruiz",
            horario = "Lun-Mie 10:00-12:00",
            aula = "A-301",
            progreso = "80%",
            evaluacion = "En curso",
            desempeno = "Bueno"
        ),
        Subject(
            id = 2,
            quarterId = 4,
            nombre = "Bases de Datos",
            clave = "IS-402",
            creditos = 5,
            profesor = "Dra. Ana Torres",
            horario = "Mar-Jue 14:00-16:00",
            aula = "A-302",
            progreso = "70%",
            evaluacion = "En curso",
            desempeno = "Regular"
        ),
        Subject(
            id = 3,
            quarterId = 4,
            nombre = "Redes de Computadoras",
            clave = "IS-403",
            creditos = 4,
            profesor = "Ing. Luis Morales",
            horario = "Vie 08:00-12:00",
            aula = "Lab-101",
            progreso = "60%",
            evaluacion = "En curso",
            desempeno = "Regular"
        ),
        // Usuario 1 - Cuatrimestre 1
        Subject(
            id = 4,
            quarterId = 1,
            nombre = "Introducción a la Programación",
            clave = "IS-101",
            creditos = 5,
            calificacion = 8.0f,
            profesor = "Mtro. Jorge Fernández"
        ),
        Subject(
            id = 5,
            quarterId = 1,
            nombre = "Matemáticas Básicas",
            clave = "IS-102",
            creditos = 4,
            calificacion = 9.0f,
            profesor = "Dra. Rosa Sánchez"
        ),
        // Usuario 1 - Cuatrimestre 2
        Subject(
            id = 6,
            quarterId = 2,
            nombre = "Estructuras de Datos",
            clave = "IS-201",
            creditos = 5,
            calificacion = 9.5f,
            profesor = "Dr. Roberto Jiménez"
        ),
        // Usuario 1 - Cuatrimestre 3
        Subject(
            id = 7,
            quarterId = 3,
            nombre = "Arquitectura de Computadoras",
            clave = "IS-301",
            creditos = 4,
            calificacion = 8.5f,
            profesor = "Ing. Pedro Sánchez"
        ),
        // Usuario 2 - Cuatrimestre 8 (activo) - 2 materias
        Subject(
            id = 8,
            quarterId = 8,
            nombre = "Gestión de Producción",
            clave = "II-401",
            creditos = 5,
            profesor = "Ing. Roberto Díaz",
            horario = "Lun-Mie 08:00-10:00",
            aula = "B-201",
            progreso = "75%",
            evaluacion = "En curso",
            desempeno = "Bueno"
        ),
        Subject(
            id = 9,
            quarterId = 8,
            nombre = "Control de Calidad",
            clave = "II-402",
            creditos = 4,
            profesor = "Dra. Laura Méndez",
            horario = "Mar-Jue 12:00-14:00",
            aula = "B-202",
            progreso = "65%",
            evaluacion = "En curso",
            desempeno = "Regular"
        ),
        // Usuario 3 - Cuatrimestre 12 (activo) - 4 materias
        Subject(
            id = 10,
            quarterId = 12,
            nombre = "Finanzas Corporativas",
            clave = "AD-401",
            creditos = 5,
            profesor = "Lic. Fernando Rojas",
            horario = "Lun-Mie 14:00-16:00",
            aula = "C-101",
            progreso = "85%",
            evaluacion = "En curso",
            desempeno = "Excelente"
        ),
        Subject(
            id = 11,
            quarterId = 12,
            nombre = "Marketing Digital",
            clave = "AD-402",
            creditos = 4,
            profesor = "Mtro. Javier López",
            horario = "Mar-Jue 10:00-12:00",
            aula = "C-102",
            progreso = "80%",
            evaluacion = "En curso",
            desempeno = "Bueno"
        ),
        Subject(
            id = 12,
            quarterId = 12,
            nombre = "Gestión de Recursos Humanos",
            clave = "AD-403",
            creditos = 5,
            profesor = "Dra. Patricia Núñez",
            horario = "Vie 09:00-13:00",
            aula = "C-103",
            progreso = "70%",
            evaluacion = "En curso",
            desempeno = "Regular"
        ),
        Subject(
            id = 13,
            quarterId = 12,
            nombre = "Contabilidad Gerencial",
            clave = "AD-404",
            creditos = 4,
            profesor = "CPA. Ricardo Soto",
            horario = "Jue 16:00-20:00",
            aula = "C-104",
            progreso = "90%",
            evaluacion = "En curso",
            desempeno = "Excelente"
        )
    )

    // Métodos para autenticación
    fun authenticate(matricula: String, password: String): User? {
        return users.find { it.matricula == matricula && it.password == password }
    }

    // Métodos para obtener datos por usuario
    fun getUserById(userId: Int): User? {
        return users.find { it.id == userId }
    }

    fun getEmergencyContactByUserId(userId: Int): EmergencyContact? {
        return emergencyContacts.find { it.userId == userId }
    }

    fun getInstitutionalInfoByUserId(userId: Int): InstitutionalInfo? {
        return institutionalInfos.find { it.userId == userId }
    }

    fun getQuartersByUserId(userId: Int): List<Quarter> {
        return quarters.filter { it.userId == userId }
    }

    fun getSubjectsByQuarterId(quarterId: Int): List<Subject> {
        return subjects.filter { it.quarterId == quarterId }
    }

    fun getActiveQuarterByUserId(userId: Int): Quarter? {
        return quarters.find { it.userId == userId && it.estado == "activo" }
    }

    fun getFinishedQuartersByUserId(userId: Int): List<Quarter> {
        return quarters.filter { it.userId == userId && it.estado == "finalizado" && it.promedio != null }
    }

    fun getAverageGradeByUserId(userId: Int): Float {
        val finishedQuarters = getFinishedQuartersByUserId(userId)
        if (finishedQuarters.isEmpty()) return 0f

        val sum = finishedQuarters.sumOf { it.promedio!!.toDouble() }
        return (sum / finishedQuarters.size).toFloat()
    }

    fun getCurrentSubjectsCountByUserId(userId: Int): Int {
        val activeQuarter = getActiveQuarterByUserId(userId)
        return if (activeQuarter != null) {
            getSubjectsByQuarterId(activeQuarter.id).size
        } else {
            0
        }
    }
    // En UserRepository agregarías métodos update
    fun updateUser(updatedUser: User) { /* actualizar en lista */ }
    fun updateInstitutionalInfo(updatedInfo: InstitutionalInfo) { /* actualizar */ }
    fun updateEmergencyContact(updatedContact: EmergencyContact) { /* actualizar */ }
}