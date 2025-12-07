package com.example.sigoforandroid

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sigoforandroid.data.repository.UserRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Estados para los campos de texto
    var matricula by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Calcular si el botón debe estar habilitado
    val isLoginEnabled = matricula.isNotBlank() && password.isNotBlank()

    // Configuración de colores y dimensiones (fáciles de modificar)
    val cardBackgroundColor = Color.White
    val cardCornerRadius = 16.dp
    val cardElevation = 8.dp
    val cardPadding = 24.dp
    val spacingBetweenElements = 16.dp
    val buttonColor = MaterialTheme.colorScheme.primary
    val forgotPasswordColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.fondooo),
            contentDescription = "Fondo login",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Column principal para distribuir verticalmente la imagen y la tarjeta
        Column(
            modifier = Modifier
                .fillMaxSize() // Ocupa todo el espacio para distribuir
                .padding(horizontal = 32.dp), // Padding general para el contenido
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra la columna verticalmente
        ) {
            // =======================================================
            // 1. BOX PARA IMÁGENES (SICO y UTM)
            // =======================================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    // Definimos una altura fija o un porcentaje para la zona de logos
                    .fillMaxHeight(0.25f) // Asigna el 25% de la altura disponible para los logos
                    .padding(bottom = 24.dp), // Espacio entre logos y la tarjeta de login
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Logo SICO
                    Image(
                        painter = painterResource(id = R.drawable.siigooo),
                        contentDescription = "Logo SICO",
                        modifier = Modifier
                            .height(110.dp)
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        contentScale = ContentScale.Fit
                    )

                    // Logo UTM
                    Image(
                        painter = painterResource(id = R.drawable.utmmmm),
                        contentDescription = "Logo UTM",
                        modifier = Modifier
                            .height(110.dp)
                            .weight(1f)
                            .padding(horizontal = 8.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            // =======================================================
            // 2. BOX PARA LA TARJETA DE LOGIN (60% de la altura)
            // =======================================================
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.60f), // Asigna el 60% de la altura restante
                contentAlignment = Alignment.TopCenter
            ) {
                // Tarjeta de login (SOLO INPUTS Y BOTONES)
                Column(
                    modifier = Modifier
                        .fillMaxSize() // Ocupa el espacio del Box(0.6f)
                        .clip(RoundedCornerShape(cardCornerRadius))
                        .background(cardBackgroundColor)
                        .shadow(elevation = cardElevation)
                        .padding(cardPadding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly // Distribuye el espacio para estirar la tarjeta
                ) {
                    // Campo de matrícula
                    OutlinedTextField(
                        value = matricula,
                        onValueChange = { matricula = it },
                        label = { Text("Matrícula") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = buttonColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = buttonColor,
                            unfocusedLabelColor = Color.Gray,
                            cursorColor = buttonColor
                        )
                    )

                    // Campo de contraseña
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = buttonColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = buttonColor,
                            unfocusedLabelColor = Color.Gray,
                            cursorColor = buttonColor
                        )
                    )

                    // Botón de iniciar sesión
                    Button(
                        onClick = {
                            val user = UserRepository.authenticate(matricula, password)
                            if (user != null) {
                                navController.navigate("principal")
                            } else {
                                showToast(context, "Matrícula o contraseña incorrecta")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = isLoginEnabled
                    ) {
                        Text("INICIAR SESIÓN")
                    }

                    // Enlace "¿Olvidaste tu contraseña?"
                    TextButton(
                        onClick = {
                            // Por ahora no hace nada
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "¿Olvidaste tu contraseña?",
                            color = forgotPasswordColor,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        } // Fin del Column principal

        // Texto en la parte inferior (se mantiene en el Box principal)
        Text(
            text = "Desarrollado por Jair y Martín",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

// Función de extensión para mostrar Toast
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview
@Composable
fun PreviewSplashScreen() {
    LoginScreen(navController = rememberNavController(), modifier = Modifier)
}