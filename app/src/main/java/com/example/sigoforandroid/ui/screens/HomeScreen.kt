package com.example.sigoforandroid.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(personFullName: String, profileName: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "¡Bienvenido!", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra el nombre completo
        Text(
            text = personFullName,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Muestra el perfil o carrera
        Text(
            text = "Perfil: $profileName",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Mensaje adicional (opcional, para confirmar que todo funciona)
        Text(
            text = "¡Acceso concedido!",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}