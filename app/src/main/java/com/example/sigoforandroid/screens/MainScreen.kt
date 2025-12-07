package com.example.sigoforandroid.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sigoforandroid.R
import com.example.sigoforandroid.data.repository.UserRepository // Asegúrate de que esta ruta sea correcta

@Composable
fun MainScreen(
    navController: NavController,
    userId: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Simulación de datos (deberías obtenerlos de tu repositorio)
    val user = remember { UserRepository.getUserById(userId) }
    val averageGrade = remember { UserRepository.getAverageGradeByUserId(userId) }
    val currentSubjectsCount = remember { UserRepository.getCurrentSubjectsCountByUserId(userId) }
    val userFullName = remember {
        if (user != null) "${user.nombres} ${user.primerApellido} ${user.segundoApellido}"
        else "Usuario no encontrado"
    }

    // Configuraciones de diseño (modificables)
    val cardColor = Color.White
    val staticCardColor = Color(0xFFF0F0F0) // Gris muy claro para tarjetas de info
    val cardCornerRadius = 16.dp
    val cardSpacing = 12.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.fondooo),
            contentDescription = "Fondo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido principal con scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 70.dp), // PADDING CRUCIAL: Deja espacio para el menú inferior fijo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(cardCornerRadius))
                    .background(Color.Gray)

                    .padding(16.dp), // Añadimos padding para que no se pegue a los bordes
                horizontalArrangement = Arrangement.SpaceBetween // CRUCIAL: Separa los elementos al máximo
            ) {
                // 1. TEXTOS (Columna izquierda)
                Column(
                    // QUITAMOS fillMaxWidth() para que solo ocupe el espacio necesario
                    // y usamos Modifier.weight(1f) para que tome el espacio disponible,
                    // sin empujar el ícono hacia afuera.
                    modifier = Modifier.weight(1f, fill = false),
                    horizontalAlignment = Alignment.Start // Alineamos los textos a la izquierda
                ) {
                    Text(
                        text = "Bienvenido de nuevo",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        // Quitamos textAlign = TextAlign.Center para que se alinee a la izquierda (Start)
                    )
                    Text(
                        text = userFullName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        // Quitamos textAlign = TextAlign.Center
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // 2. ÍCONO (Derecha)
                Image(
                    painter = painterResource(R.drawable.usericon),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp) // Añadido un tamaño fijo para el ícono
                )
            }
            // Sección: ACCESOS RÁPIDOS
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(cardSpacing)
            ) {

                // Título de sección
                Text(
                    text = "ACCESOS RÁPIDOS",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // =======================================================
                // CONTENEDOR DE RESUMEN ACADÉMICO (TARJETAS DE INFO)
                // =======================================================
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(cardCornerRadius))
                        .background(Color.White.copy(alpha = 0.9f)) // Fondo blanco semitransparente
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Resumen Académico",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(cardSpacing)
                    ) {
                        // Tarjeta 1: Promedio General
                        InfoCard(
                            title = "Promedio General",
                            value = String.format("%.1f", averageGrade),
                            cardColor = staticCardColor,
                            cardCornerRadius = cardCornerRadius,
                            modifier = Modifier.weight(1f)
                        )

                        // Tarjeta 2: Materias cursando
                        InfoCard(
                            title = "Materias cursando",
                            value = currentSubjectsCount.toString(),
                            cardColor = staticCardColor,
                            cardCornerRadius = cardCornerRadius,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(cardSpacing))

                // =======================================================
                // TARJETAS DE NAVEGACIÓN
                // =======================================================

                // Tarjeta 3: Historial Académico
                NavCard(
                    title = "Historial Académico",
                    imageResId = R.drawable.historia,
                    cardColor = cardColor,
                    cardCornerRadius = cardCornerRadius,
                    onClick = { navController.navigate("historial/$userId") }
                )

                // Tarjeta 4: Mi Perfil
                NavCard(
                    title = "Mi Perfil",
                    imageResId = R.drawable.usericon,
                    cardColor = cardColor,
                    cardCornerRadius = cardCornerRadius,
                    onClick = { navController.navigate("perfil/$userId") }
                )

                // Tarjeta 5: Horario
                NavCard(
                    title = "Horario",
                    imageResId = R.drawable.horariooo,
                    cardColor = cardColor,
                    cardCornerRadius = cardCornerRadius,
                    onClick = { navController.navigate("horario/$userId") }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
        }

        // Menú inferior fijo
        BottomMenu(
            onLogout = {
                navController.popBackStack()
                navController.navigate("login") {
                    popUpTo("login") { inclusive = true }
                }
            },
            onMoreOptions = {
                showToast(context, "Más opciones - Próximamente")
            },
            modifier = Modifier.align(Alignment.BottomCenter) // AHORA SE ANCLA AL FONDO
        )
    }
}

// =======================================================
// COMPOSABLES DE TARJETAS (Ajustadas para el nuevo diseño)
// =======================================================

@Composable
fun InfoCard(
    title: String,
    value: String,
    cardColor: Color,
    cardCornerRadius: Dp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(cardCornerRadius)),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp) // Reducido padding para un diseño más compacto
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                fontSize = 28.sp, // Tamaño ajustado para encajar
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.primary // Usar color primario
            )
            Text(
                text = title,
                fontSize = 12.sp, // Tamaño ajustado
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NavCard(
    title: String,
    imageResId: Int,
    cardColor: Color,
    cardCornerRadius: Dp,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(cardCornerRadius)),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Imagen
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = title,
                    modifier = Modifier.size(36.dp)
                )

                // Texto
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }

            // Icono de flecha
            Icon(
                painter = painterResource(R.drawable.ir), // Asegúrate que R.drawable.ir exista
                contentDescription = "Ir",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@Composable
fun BottomMenu(
    onLogout: () -> Unit,
    onMoreOptions: () -> Unit,
    modifier: Modifier = Modifier // AÑADIDO: Modificador para la alineación
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.White.copy(alpha = 0.9f)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Botón Cerrar Sesión
        IconButton(
            onClick = onLogout,
            modifier = Modifier.size(56.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Inicio",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Inicio",
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }
        // Botón Cerrar Sesión
        IconButton(
            onClick = onLogout,
            modifier = Modifier.size(56.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.salida),
                    contentDescription = "Cerrar sesión",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Salir",
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }

        // Botón Más Opciones
        IconButton(
            onClick = onMoreOptions,
            modifier = Modifier.size(56.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = "Más opciones",
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Más",
                    fontSize = 10.sp,
                    color = Color.Black
                )
            }
        }
    }
}

// Función auxiliar para mostrar Toast
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

// =======================================================
// PREVIEW
// =======================================================

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController = navController, userId = 1)
}