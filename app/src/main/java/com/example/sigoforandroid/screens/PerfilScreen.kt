package com.example.sigoforandroid.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sigoforandroid.R
import com.example.sigoforandroid.data.model.EmergencyContact
import com.example.sigoforandroid.data.model.InstitutionalInfo
import com.example.sigoforandroid.data.model.User
import com.example.sigoforandroid.data.repository.UserRepository
@Composable
fun PerfilScreen(
    navController: NavController,
    userId: Int
) {
    val context = LocalContext.current

    // Obtener datos del usuario
    val user = remember { UserRepository.getUserById(userId) }
    val institutionalInfo = remember { UserRepository.getInstitutionalInfoByUserId(userId) }
    val emergencyContact = remember { UserRepository.getEmergencyContactByUserId(userId) }

    // Estados para expansión de secciones
    var expandedSection by remember { mutableStateOf(1) } // 1=Personal, 2=Institucional, 3=Contacto, 4=Emergencia

    // Estados para modo edición por sección
    var editPersonal by remember { mutableStateOf(false) }
    var editInstitutional by remember { mutableStateOf(false) }
    var editContact by remember { mutableStateOf(false) }
    var editEmergency by remember { mutableStateOf(false) }

    // Estados para datos editados
    var editedUser by remember { mutableStateOf(user?.copy() ?: User(
        id = 0, matricula = "", username = "", password = "", nombres = "",
        primerApellido = "", segundoApellido = "", fechaNacimiento = "",
        sexo = "", curp = "", numeroSeguridadSocial = "", telefonoPersonal = "",
        emailPersonal = "", carrera = "", semestre = 0
    )) }

    var editedInstitutionalInfo by remember { mutableStateOf(institutionalInfo?.copy() ?: InstitutionalInfo(
        id = 0, userId = 0, emailInstitucional = "", passwordEmail = "",
        usuarioELibro = "", passwordELibro = ""
    )) }

    var editedEmergencyContact by remember { mutableStateOf(emergencyContact?.copy() ?: EmergencyContact(
        id = 0, userId = 0, nombreCompleto = "", parentesco = "",
        telefonoCelular = "", telefonoCasa = "", telefonoTrabajo = "", extension = ""
    )) }

    // Configuraciones de diseño
    val cardColor = Color.LightGray
    val cardCornerRadius = 16.dp
    val cardPadding = 16.dp
    val sectionSpacing = 12.dp
    val editedFieldBorderColor = Color.Green
    val normalFieldBorderColor = Color.Gray

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

                // Título de la pantalla
                Text(
                    text = "MI PERFIL",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.clip(RoundedCornerShape(cardCornerRadius))
                    .background(Color.Gray.copy(alpha = 0.8f))

                        .padding(bottom = 10.dp, start = 24.dp, end = 24.dp, top = 10.dp)
                )
                Spacer(modifier = Modifier.height(40.dp))
                // Sección 1: INFORMACIÓN PERSONAL
                ExpandableSection(
                    title = "INFORMACIÓN PERSONAL",
                    isExpanded = expandedSection == 1,
                    onExpandToggle = {
                        expandedSection = if (expandedSection == 1) 0 else 1
                        editPersonal = false
                    },
                    cardColor = cardColor,
                    cardCornerRadius = cardCornerRadius,
                    cardPadding = cardPadding
                ) {
                    PersonalInfoForm(
                        user = editedUser,
                        onUserChange = { editedUser = it },
                        isEditing = editPersonal,
                        borderColor = if (editPersonal) editedFieldBorderColor else normalFieldBorderColor,
                        onSave = {
                            if (editedUser.nombres.isBlank() || editedUser.primerApellido.isBlank() ||
                                editedUser.fechaNacimiento.isBlank() || editedUser.sexo.isBlank() ||
                                editedUser.curp.isBlank() || editedUser.numeroSeguridadSocial.isBlank()) {
                                showToast(context, "Todos los campos son obligatorios")
                                return@PersonalInfoForm
                            }
                            editPersonal = false
                            showToast(context, "Información personal actualizada correctamente")
                        },
                        onEditToggle = { editPersonal = !editPersonal }
                    )
                }

                Spacer(modifier = Modifier.height(sectionSpacing))

                // Sección 2: INFORMACIÓN INSTITUCIONAL
                ExpandableSection(
                    title = "INFORMACIÓN INSTITUCIONAL",
                    isExpanded = expandedSection == 2,
                    onExpandToggle = {
                        expandedSection = if (expandedSection == 2) 0 else 2
                        editInstitutional = false
                    },
                    cardColor = cardColor,
                    cardCornerRadius = cardCornerRadius,
                    cardPadding = cardPadding
                ) {
                    InstitutionalInfoForm(
                        institutionalInfo = editedInstitutionalInfo,
                        onInstitutionalInfoChange = { editedInstitutionalInfo = it },
                        isEditing = editInstitutional,
                        borderColor = if (editInstitutional) editedFieldBorderColor else normalFieldBorderColor,
                        onSave = {
                            if (editedInstitutionalInfo.passwordEmail.isBlank() ||
                                editedInstitutionalInfo.passwordELibro.isBlank()) {
                                showToast(context, "Las contraseñas son obligatorias")
                                return@InstitutionalInfoForm
                            }
                            editInstitutional = false
                            showToast(context, "Información institucional actualizada correctamente")
                        },
                        onEditToggle = { editInstitutional = !editInstitutional }
                    )
                }

                Spacer(modifier = Modifier.height(sectionSpacing))

                // Sección 3: INFORMACIÓN DE CONTACTO
                ExpandableSection(
                    title = "INFORMACIÓN DE CONTACTO",
                    isExpanded = expandedSection == 3,
                    onExpandToggle = {
                        expandedSection = if (expandedSection == 3) 0 else 3
                        editContact = false
                    },
                    cardColor = cardColor,
                    cardCornerRadius = cardCornerRadius,
                    cardPadding = cardPadding
                ) {
                    ContactInfoForm(
                        user = editedUser,
                        onUserChange = { editedUser = it },
                        isEditing = editContact,
                        borderColor = if (editContact) editedFieldBorderColor else normalFieldBorderColor,
                        onSave = {
                            if (editedUser.telefonoPersonal.length != 10) {
                                showToast(context, "El teléfono debe tener 10 dígitos")
                                return@ContactInfoForm
                            }
                            if (!editedUser.emailPersonal.contains("@")) {
                                showToast(context, "Email inválido")
                                return@ContactInfoForm
                            }
                            editContact = false
                            showToast(context, "Información de contacto actualizada correctamente")
                        },
                        onEditToggle = { editContact = !editContact }
                    )
                }

                Spacer(modifier = Modifier.height(sectionSpacing))

                // Sección 4: CONTACTO DE EMERGENCIA
                ExpandableSection(
                    title = "CONTACTO DE EMERGENCIA",
                    isExpanded = expandedSection == 4,
                    onExpandToggle = {
                        expandedSection = if (expandedSection == 4) 0 else 4
                        editEmergency = false
                    },
                    cardColor = cardColor,
                    cardCornerRadius = cardCornerRadius,
                    cardPadding = cardPadding
                ) {
                    EmergencyContactForm(
                        emergencyContact = editedEmergencyContact,
                        onEmergencyContactChange = { editedEmergencyContact = it },
                        isEditing = editEmergency,
                        borderColor = if (editEmergency) editedFieldBorderColor else normalFieldBorderColor,
                        onSave = {
                            if (editedEmergencyContact.nombreCompleto.isBlank() ||
                                editedEmergencyContact.parentesco.isBlank() ||
                                editedEmergencyContact.telefonoCelular.isBlank()) {
                                showToast(context, "Nombre, parentesco y teléfono celular son obligatorios")
                                return@EmergencyContactForm
                            }
                            if (editedEmergencyContact.telefonoCelular.length != 10) {
                                showToast(context, "Teléfono celular debe tener 10 dígitos")
                                return@EmergencyContactForm
                            }
                            if (editedEmergencyContact.telefonoCasa.isNotBlank() &&
                                editedEmergencyContact.telefonoCasa.length != 10) {
                                showToast(context, "Teléfono casa debe tener 10 dígitos")
                                return@EmergencyContactForm
                            }
                            if (editedEmergencyContact.telefonoTrabajo.isNotBlank() &&
                                editedEmergencyContact.telefonoTrabajo.length != 10) {
                                showToast(context, "Teléfono trabajo debe tener 10 dígitos")
                                return@EmergencyContactForm
                            }
                            editEmergency = false
                            showToast(context, "Contacto de emergencia actualizado correctamente")
                        },
                        onEditToggle = { editEmergency = !editEmergency }
                    )
                }

                Spacer(modifier = Modifier.height(80.dp)) // Espacio al final para que no tape el contenido
            }
        }

        // MENÚ INFERIOR (SIEMPRE ABAJO)
        BottomMenuProfile(
            onHome = {
                navController.navigate("main/$userId") {
                    popUpTo("main/$userId") { inclusive = false }
                }
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

@Composable
fun ExpandableSection(
    title: String,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit,
    cardColor: Color,
    cardCornerRadius: androidx.compose.ui.unit.Dp,
    cardPadding: androidx.compose.ui.unit.Dp,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(cardCornerRadius)),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column {
            // Header de la sección
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(cardPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

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

            // Contenido (sólo visible si está expandido)
            if (isExpanded) {
                content()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalInfoForm(
    user: User,
    onUserChange: (User) -> Unit,
    isEditing: Boolean,
    borderColor: Color,
    onSave: () -> Unit,
    onEditToggle: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Fila 1: Nombres y Primer Apellido
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Nombres
            OutlinedTextField(
                value = user.nombres,
                onValueChange = { onUserChange(user.copy(nombres = it)) },
                label = { Text("Nombres") },
                modifier = Modifier.weight(1f),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )

            // Primer Apellido
            OutlinedTextField(
                value = user.primerApellido,
                onValueChange = { onUserChange(user.copy(primerApellido = it)) },
                label = { Text("Primer Apellido") },
                modifier = Modifier.weight(1f),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )
        }

        // Fila 2: Segundo Apellido (opcional) y Fecha Nacimiento
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Segundo Apellido
            OutlinedTextField(
                value = user.segundoApellido,
                onValueChange = { onUserChange(user.copy(segundoApellido = it)) },
                label = { Text("Segundo Apellido (opcional)") },
                modifier = Modifier.weight(1f),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )

            // Fecha Nacimiento
            OutlinedTextField(
                value = user.fechaNacimiento,
                onValueChange = { onUserChange(user.copy(fechaNacimiento = it)) },
                label = { Text("Fecha Nacimiento") },
                modifier = Modifier.weight(1f),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )
        }

        // Fila 3: Sexo y CURP
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Sexo
            OutlinedTextField(
                value = user.sexo,
                onValueChange = { onUserChange(user.copy(sexo = it)) },
                label = { Text("Sexo") },
                modifier = Modifier.weight(1f),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )

            // CURP
            OutlinedTextField(
                value = user.curp,
                onValueChange = { onUserChange(user.copy(curp = it)) },
                label = { Text("CURP") },
                modifier = Modifier.weight(1f),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )
        }

        // Número de Seguridad Social
        OutlinedTextField(
            value = user.numeroSeguridadSocial,
            onValueChange = { onUserChange(user.copy(numeroSeguridadSocial = it)) },
            label = { Text("Número de Seguridad Social") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )

        // Botón ACTUALIZAR/GUARDAR
        Button(
            onClick = {
                if (isEditing) {
                    onSave()
                } else {
                    onEditToggle()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isEditing) "GUARDAR" else "ACTUALIZAR")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstitutionalInfoForm(
    institutionalInfo: InstitutionalInfo,
    onInstitutionalInfoChange: (InstitutionalInfo) -> Unit,
    isEditing: Boolean,
    borderColor: Color,
    onSave: () -> Unit,
    onEditToggle: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Correo Institucional (sólo lectura)
        Text(
            text = "CORREO ELECTRÓNICO INSTITUCIONAL",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Este es tu correo electrónico institucional, entra en Gmail de Google.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        OutlinedTextField(
            value = institutionalInfo.emailInstitucional,
            onValueChange = { /* No editable */ },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            )
        )

        OutlinedTextField(
            value = institutionalInfo.passwordEmail,
            onValueChange = { onInstitutionalInfoChange(institutionalInfo.copy(passwordEmail = it)) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isEditing) borderColor else Color.LightGray,
                unfocusedBorderColor = if (isEditing) borderColor else Color.LightGray
            )
        )

        Text(
            text = "IMPORTANTE: El correo electrónico estará activo una semana después del inicio del cuatrimestre.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
        )

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Acceso a E-Libro
        Text(
            text = "ACCESO A E-LIBRO",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Este es tu usuario y contraseña para entrar a e-libro",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        OutlinedTextField(
            value = institutionalInfo.usuarioELibro,
            onValueChange = { /* No editable */ },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.LightGray,
                unfocusedBorderColor = Color.LightGray
            )
        )

        OutlinedTextField(
            value = institutionalInfo.passwordELibro,
            onValueChange = { onInstitutionalInfoChange(institutionalInfo.copy(passwordELibro = it)) },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isEditing) borderColor else Color.LightGray,
                unfocusedBorderColor = if (isEditing) borderColor else Color.LightGray
            )
        )

        // Botón ACTUALIZAR/GUARDAR
        Button(
            onClick = {
                if (isEditing) {
                    onSave()
                } else {
                    onEditToggle()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isEditing) "GUARDAR" else "ACTUALIZAR")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactInfoForm(
    user: User,
    onUserChange: (User) -> Unit,
    isEditing: Boolean,
    borderColor: Color,
    onSave: () -> Unit,
    onEditToggle: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "INFORMACIÓN DE CONTACTO",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )

        // Teléfono
        OutlinedTextField(
            value = user.telefonoPersonal,
            onValueChange = { onUserChange(user.copy(telefonoPersonal = it)) },
            label = { Text("Teléfono (10 Dígitos)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )

        // Correo Electrónico Personal
        OutlinedTextField(
            value = user.emailPersonal,
            onValueChange = { onUserChange(user.copy(emailPersonal = it)) },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )

        // Botón ACTUALIZAR/GUARDAR
        Button(
            onClick = {
                if (isEditing) {
                    onSave()
                } else {
                    onEditToggle()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isEditing) "GUARDAR" else "ACTUALIZAR")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmergencyContactForm(
    emergencyContact: EmergencyContact,
    onEmergencyContactChange: (EmergencyContact) -> Unit,
    isEditing: Boolean,
    borderColor: Color,
    onSave: () -> Unit,
    onEditToggle: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "CONTACTO DE EMERGENCIA",
            style = MaterialTheme.typography.labelMedium,
            color = Color.Black
        )

        // Nombre Completo
        OutlinedTextField(
            value = emergencyContact.nombreCompleto,
            onValueChange = { onEmergencyContactChange(emergencyContact.copy(nombreCompleto = it)) },
            label = { Text("Nombre Completo") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )

        // Parentesco/Relación
        OutlinedTextField(
            value = emergencyContact.parentesco,
            onValueChange = { onEmergencyContactChange(emergencyContact.copy(parentesco = it)) },
            label = { Text("Parentesco/Relación") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )

        // Teléfono Celular
        OutlinedTextField(
            value = emergencyContact.telefonoCelular,
            onValueChange = { onEmergencyContactChange(emergencyContact.copy(telefonoCelular = it)) },
            label = { Text("Teléfono Celular (10 Dígitos)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )

        // Fila: Teléfono Casa y Teléfono Trabajo
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Teléfono Casa (opcional)
            OutlinedTextField(
                value = emergencyContact.telefonoCasa,
                onValueChange = { onEmergencyContactChange(emergencyContact.copy(telefonoCasa = it)) },
                label = { Text("Teléfono Casa (opcional)") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )

            // Teléfono Trabajo (opcional)
            OutlinedTextField(
                value = emergencyContact.telefonoTrabajo,
                onValueChange = { onEmergencyContactChange(emergencyContact.copy(telefonoTrabajo = it)) },
                label = { Text("Teléfono Trabajo (opcional)") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                readOnly = !isEditing,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )
        }

        // Extensión (opcional)
        OutlinedTextField(
            value = emergencyContact.extension,
            onValueChange = { onEmergencyContactChange(emergencyContact.copy(extension = it)) },
            label = { Text("Extensión (opcional)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            readOnly = !isEditing,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor
            )
        )

        // Botón ACTUALIZAR/GUARDAR
        Button(
            onClick = {
                if (isEditing) {
                    onSave()
                } else {
                    onEditToggle()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(if (isEditing) "GUARDAR" else "ACTUALIZAR")
        }
    }
}

// MENÚ INFERIOR CORREGIDO
@Composable
fun BottomMenuProfile(
    onHome: () -> Unit,
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
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Inicio",
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
                    modifier = Modifier.size(32.dp)
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

// Función auxiliar para mostrar Toast
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPerfilScreen() {
    val navController = rememberNavController()
    // Asegúrate de que el userId 1 devuelva datos simulados válidos en tu UserRepository
    PerfilScreen(navController = navController, userId = 1)
}