package com.example.sigoforandroid.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PerfilScreen(
    navController: NavController,
    userId: Int
) {
    Text("Mi Perfil - Usuario ID: $userId")
}