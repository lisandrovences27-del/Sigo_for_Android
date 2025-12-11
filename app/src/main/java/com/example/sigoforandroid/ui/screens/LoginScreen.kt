package com.example.sigoforandroid.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
// 🛑 Usaremos TextField, no OutlinedTextField para evitar el error persistente
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sigoforandroid.R
import com.example.sigoforandroid.ui.theme.UtmDarkBlue
import com.example.sigoforandroid.ui.theme.UtmGreen
import com.example.sigoforandroid.ui.theme.UtmLightBlue
import com.example.sigoforandroid.ui.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush =  Brush.horizontalGradient(
                    colors = listOf(UtmDarkBlue, UtmLightBlue)
                )

            )
    ) {
        // --- SECCIÓN SUPERIOR (LOGOS) ---
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 28.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sigoooi),
                    contentDescription = "Logo SIGO",
                    modifier = Modifier.height(65.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier.height(40.dp).width(1.dp).background(Color.White)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.utmi),
                    contentDescription = "Logo UTM",
                    modifier = Modifier.height(65.dp),
                    contentScale = ContentScale.Fit
                )
            }

        }

        // --- SECCIÓN INFERIOR (TARJETA BLANCA) ---
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .fillMaxHeight(0.60f),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(32.dp).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                CustomTextField(
                    value = viewModel.username,
                    onValueChange = { viewModel.username = it },
                    placeholder = "Usuario o Matrícula",
                    icon = Icons.Default.Person,

                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    placeholder = "Contraseña",
                    icon = Icons.Default.Lock,
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                if (viewModel.errorMessage != null) {
                    Text(
                        text = viewModel.errorMessage!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                Button(
                    onClick = { viewModel.performLogin() },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = UtmGreen),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Text(
                            text = "INICIAR SESION",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                val annotatedString = buildAnnotatedString {
                    pushStringAnnotation(tag = "Forgot", annotation = "forgot_password")
                    pushStyle(SpanStyle(textDecoration = TextDecoration.Underline, fontWeight = FontWeight.Bold, fontSize = 12.sp))
                    append("¿Olvidaste tu contraseña?")
                    pop()
                    pop()
                }

                ClickableText(text = annotatedString, onClick = { /* TODO: Implementar navegación */ })
            }
        }
    }
}


// 🛑 FUNCIÓN CORREGIDA USANDO TextField SIMPLE Y COLORES PERSONALIZADOS 🛑
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    TextField( // <--- ¡Cambiamos a TextField!
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,

        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
        },
        visualTransformation = if (isPassword) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },

        // Simulación de OutlinedTextField usando TextFieldDefaults.colors
        colors = TextFieldDefaults.colors(
            // --- Colores del Contenedor/Fondo ---
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,

            // --- Colores del Texto (LA CLAVE) ---
            // Color del texto que el usuario escribe cuando el campo está enfocado (seleccionado)
            focusedTextColor = Color.Black,
            // Color del texto que el usuario escribe cuando el campo NO está enfocado
            unfocusedTextColor = Color.Black,

            // --- Colores de Bordes (Indicadores) y Elementos ---
            focusedIndicatorColor = UtmGreen,
            unfocusedIndicatorColor = Color.LightGray,
            disabledIndicatorColor = Color.LightGray,

            // Color del texto de ayuda ("Usuario o Matrícula")
            focusedPlaceholderColor = Color.Gray,
            unfocusedPlaceholderColor = Color.Gray,

            // Color de los íconos (el candado y el usuario)
            focusedLeadingIconColor = UtmGreen,
            unfocusedLeadingIconColor = Color.Black,

            // Color del cursor
            cursorColor = UtmGreen
        ),

        // Aplicamos la forma delineada
        shape = RoundedCornerShape(8.dp)
    )
}