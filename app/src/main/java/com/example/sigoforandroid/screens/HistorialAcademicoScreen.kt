package com.example.sigoforandroid.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sigoforandroid.R
import com.example.sigoforandroid.data.repository.UserRepository

@Composable
fun HistorialAcademicoScreen(
    navController: NavController,
    userId: Int
) {
    // Obtener datos del usuario
    val quarters = remember {
        UserRepository.getQuartersByUserId(userId).sortedByDescending { it.id }
    }
    val finishedQuarters = remember {
        UserRepository.getFinishedQuartersByUserId(userId)
    }
    val averageGrade = remember {
        UserRepository.getAverageGradeByUserId(userId)
    }

    // Estados para expansión de cuatrimestres
    val expandedQuarters = remember { mutableSetOf<Int>() }

    // Configuraciones de diseño
    val activeCardColor = Color(0xFFC8E6C9) // Verde claro
    val finishedCardColor = Color.LightGray // Gris claro
    val activeStatusColor = Color(0xFF388E3C) // Verde oscuro
    val finishedStatusColor = Color.Gray // Gris
    val cardCornerRadius = 16.dp
    val cardPadding = 16.dp
    val sectionSpacing = 12.dp

    // ESTRUCTURA PRINCIPAL CORREGIDA
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // CONTENIDO PRINCIPAL (con scroll)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f) // Esto hace que ocupe todo el espacio disponible
        ) {
            // Fondo con imagen
            Image(
                painter = painterResource(id = R.drawable.fondooo),
                contentDescription = "Fondo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Contenido con scroll
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // Header de la pantalla
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "HISTORIAL ACADÉMICO",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.Gray.copy(alpha = 0.9f)).padding(30.dp, 10.dp, 30.dp, 10.dp)
                    )

                    Text(
                        text = "Promedio General: ${String.format("%.1f", averageGrade)}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = "Cuatrimestres: ${finishedQuarters.size} finalizados",
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Lista de cuatrimestres
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(sectionSpacing)
                ) {
                    if (quarters.isEmpty()) {
                        // Mensaje si no hay cuatrimestres
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(cardCornerRadius)),
                            colors = CardDefaults.cardColors(containerColor = finishedCardColor)
                        ) {
                            Text(
                                text = "No hay cuatrimestres registrados",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(cardPadding),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    } else {
                        quarters.forEach { quarter ->
                            QuarterCard(
                                quarter = quarter,
                                isExpanded = expandedQuarters.contains(quarter.id),
                                onExpandToggle = {
                                    if (expandedQuarters.contains(quarter.id)) {
                                        expandedQuarters.remove(quarter.id)
                                    } else {
                                        expandedQuarters.add(quarter.id)
                                    }
                                },
                                cardColor = if (quarter.estado == "activo") activeCardColor else finishedCardColor,
                                statusColor = if (quarter.estado == "activo") activeStatusColor else finishedStatusColor,
                                cardCornerRadius = cardCornerRadius,
                                cardPadding = cardPadding
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(80.dp)) // Espacio al final
            }
        }

        // MENÚ INFERIOR (SIEMPRE ABAJO)
        BottomMenuHistorial(
            onHome = {
                navController.navigate("main/$userId") {
                    popUpTo("main/$userId") { inclusive = false }
                }
            },
            onBack = {
                navController.popBackStack()
            },
            onLogout = {
                navController.popBackStack()
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }
}

// ================= COMPONENTES =================

// Componente: Tarjeta de cuatrimestre
@Composable
fun QuarterCard(
    quarter: com.example.sigoforandroid.data.model.Quarter,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    cardColor: Color,
    statusColor: Color,
    cardCornerRadius: androidx.compose.ui.unit.Dp,
    cardPadding: androidx.compose.ui.unit.Dp
) {
    val subjects = remember {
        UserRepository.getSubjectsByQuarterId(quarter.id)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cardCornerRadius)),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column {
            // Header del cuatrimestre (siempre visible)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(cardPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Información principal
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = quarter.nombre,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        // Estado
                        Text(
                            text = quarter.estado.uppercase(),
                            color = statusColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                                .background(
                                    statusColor.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Text(
                        text = "Periodo: ${quarter.periodo}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Text(
                        text = "Grupo: ${quarter.grupo}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )

                    if (quarter.estado == "finalizado" && quarter.promedio != null) {
                        Text(
                            text = "Promedio: ${String.format("%.1f", quarter.promedio)}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }

                // Botón para expandir/colapsar
                IconButton(
                    onClick = onExpandToggle,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isExpanded) R.drawable.abajo
                            else R.drawable.arriba
                        ),
                        contentDescription = if (isExpanded) "Colapsar" else "Expandir",
                        tint = Color.Black
                    )
                }
            }

            // Contenido expandido (sólo visible si está expandido)
            if (isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = cardPadding, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Información del cuatrimestre
                    Column {
                        Text(
                            text = "INFORMACIÓN DEL CUATRIMESTRE",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )

                        InfoRow(label = "Carrera:", value = quarter.carrera)
                        InfoRow(label = "Tutor:", value = quarter.tutor)
                        InfoRow(label = "Fecha inicio:", value = quarter.fechaInicio)
                        InfoRow(
                            label = "Fecha fin:",
                            value = quarter.fechaFin ?: "En curso"
                        )
                    }

                    Divider(
                        color = Color.Gray.copy(alpha = 0.3f),
                        thickness = 1.dp
                    )

                    // Materias del cuatrimestre
                    Column {
                        Text(
                            text = "MATERIAS (${subjects.size})",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        if (subjects.isEmpty()) {
                            Text(
                                text = "No hay materias registradas",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                            )
                        } else {
                            subjects.forEach { subject ->
                                SubjectItem(subject = subject, quarterEstado = quarter.estado)
                                if (subject != subjects.last()) {
                                    Divider(
                                        color = Color.Gray.copy(alpha = 0.2f),
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Componente: Ítem de materia
@Composable
fun SubjectItem(
    subject: com.example.sigoforandroid.data.model.Subject,
    quarterEstado: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        // Nombre y clave
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = subject.nombre,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Clave: ${subject.clave}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            // Créditos
            Text(
                text = "${subject.creditos} créditos",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }

        // Profesor
        Text(
            text = "Profesor: ${subject.profesor}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )

        // Calificación o estado
        if (quarterEstado == "finalizado" && subject.calificacion != null) {
            Text(
                text = "Calificación: ${String.format("%.1f", subject.calificacion)}",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        } else if (quarterEstado == "activo") {
            // Información adicional para materias activas
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InfoChip(label = "Estado:", value = subject.evaluacion ?: "En curso")
                    InfoChip(label = "Progreso:", value = subject.progreso ?: "N/A")
                    InfoChip(label = "Desempeño:", value = subject.desempeno ?: "N/A")
                }

                if (subject.horario != null) {
                    Text(
                        text = "Horario: ${subject.horario}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }

                if (subject.aula != null) {
                    Text(
                        text = "Aula: ${subject.aula}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }
            }
        } else {
            Text(
                text = "Estado: En curso",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

// Componente: Fila de información
@Composable
fun InfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

// Componente: Chip de información
@Composable
fun InfoChip(
    label: String,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            fontWeight = FontWeight.Medium
        )
    }
}

// MENÚ INFERIOR CORREGIDO
@Composable
fun BottomMenuHistorial(
    onHome: () -> Unit,
    onBack: () -> Unit,
    onLogout: () -> Unit
) {
    Surface(
        tonalElevation = 8.dp,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón Inicio
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable(onClick = onHome)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Inicio",
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "Inicio",
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }

            // Botón Regresar
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable(onClick = onBack)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.volver),
                    contentDescription = "Regresar",
                    modifier = Modifier.size(28.dp),
                    tint = Color.Black
                )
                Text(
                    text = "Regresar",
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }

            // Botón Cerrar Sesión
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable(onClick = onLogout)
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.salida),
                    contentDescription = "Cerrar sesión",
                    modifier = Modifier.size(28.dp)
                )
                Text(
                    text = "Salir",
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHistorialAcademicoScreen() {
    val navController = rememberNavController()
    // ID simulado para el Preview
    HistorialAcademicoScreen(navController = navController, userId = 1)
}