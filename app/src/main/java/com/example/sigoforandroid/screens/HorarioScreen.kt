package com.example.sigoforandroid.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HorarioScreen(
    navController: NavController,
    userId: Int
) {
    Text("Horario - Usuario ID: $userId")
}