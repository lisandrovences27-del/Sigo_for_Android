package com.example.sigoforandroid.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sigoforandroid.data.model.LoginResponse
import com.example.sigoforandroid.ui.theme.UtmDarkBlue
import com.example.sigoforandroid.ui.viewmodel.LoginViewModel

// La función ahora usa un parámetro 'studentData' que es el objeto completo de la API
@Composable
fun HomeScreen(
    studentData: LoginResponse,
    // Pasamos el ViewModel para poder llamar a la función de cerrar sesión
    viewModel: LoginViewModel = viewModel()
) {
    val infoList = createInfoList(studentData)

    Scaffold(
        topBar = {
            // Añadimos el botón de Cerrar Sesión
            HomeTopBar(onLogoutClick = { viewModel.logout() })
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color((0xFFE4E5E5)
                ))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Datos del Alumno",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = UtmDarkBlue,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Usamos items() para iterar sobre la lista de pares (Clave, Valor)
            items(infoList) { (key, value) ->
                InfoCard(key, value)
            }
        }
    }
}

// 🛑 Componente de la Barra Superior, ahora con botón de Cerrar Sesión 🛑
@Composable
fun HomeTopBar(onLogoutClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color(0xFF33C999)),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SIGO - UTM",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp)
            )

            // Botón/Icono de Cerrar Sesión
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Cerrar Sesión",
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
                    .clickable(onClick = onLogoutClick)
            )
        }
    }
}

// Función Composable para cada tarjeta de información (se mantiene igual)
@Composable
fun InfoCard(key: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = key,
                fontSize = 12.sp,
                color = UtmDarkBlue,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// 🛑 Función auxiliar para mapear todos los datos relevantes del JSON 🛑
fun createInfoList(data: LoginResponse): List<Pair<String, String>> {
    return listOf(
        "Nombre Completo" to data.personFullName,
        "Perfil Asignado" to data.profileName,
        "Usuario/Matrícula" to data.username,
        "Correo Electrónico" to data.email,
        "ID de Persona" to data.personId.toString(),
        "ID de Registro" to data.id.toString(),
        "Módulo de Acceso" to data.accessModule,
        "Roles" to data.roles.joinToString(),
        "Fecha de Registro" to data.register,
        "Términos Aceptados" to if (data.termsConditions) "Sí" else "No",
        "Cuenta Activa" to if (data.active) "Sí" else "No"
    )
}