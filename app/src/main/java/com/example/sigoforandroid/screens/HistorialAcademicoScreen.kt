package com.example.sigoforandroid.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HistorialAcademicoScreen(
    navController: NavController,
    userId: Int
) {
    Text("Historial Académico - Usuario ID: $userId")
}