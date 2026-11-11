package com.example.aprendefacilingles

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPerfil(contador: Int) {
    val context = LocalContext.current

    // 1. Estados de los campos de texto
    var nombreUsuario by remember { mutableStateOf("") }
    var objetivoAprendizaje by remember { mutableStateOf("") }

    // 2. Estados de Fecha y Hora
    var mostrarModalFecha by remember { mutableStateOf(false) }
    var fechaMilisegundos by remember { mutableStateOf<Long?>(null) }
    var mostrarModalHora by remember { mutableStateOf(false) }
    var horaSeleccionada by remember { mutableStateOf("") }

    // 3. Estado de los RadioButtons (Nivel de partida: A1, A2, B1, B2, C1, C2)
    val opcionesNivel = listOf("A1", "A2", "B1", "B2", "C1", "C2")
    var nivelSeleccionado by remember { mutableStateOf(opcionesNivel[0]) }

    // 4. Estado de los Checkboxes (Áreas de estudio a reforzar)
    val opcionesAreas = listOf("Vocabulario", "Gramática", "Conversación", "Listening")
    var areasSeleccionadas by remember { mutableStateOf(setOf<String>()) }

    // 5. Estados de Switches / Términos
    var recordatoriosActivos by remember { mutableStateOf(false) }
    var terminosAceptados by remember { mutableStateOf(false) }

    // Preparación del texto de la fecha
    val fechaFormateada = if (fechaMilisegundos != null) {
        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formato.format(Date(fechaMilisegundos!!))
    } else ""

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cabecera con Puntos Acumulados
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Perfil del Estudiante", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Puntos acumulados: $contador", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary)
                }
            }

            Text(text = "Configura tu perfil de aprendizaje", style = MaterialTheme.typography.titleLarge)

            // --- CAMPOS DE TEXTO ---
            OutlinedTextField(
                value = nombreUsuario,
                onValueChange = { nombreUsuario = it },
                label = { Text("Nombre de usuario / Alias") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = objetivoAprendizaje,
                onValueChange = { objetivoAprendizaje = it },
                label = { Text("Objetivo de aprendizaje (ej. Viajar, Negocios)") },
                modifier = Modifier.fillMaxWidth()
            )

            // --- FECHA OBJETIVO ---
            OutlinedTextField(
                value = fechaFormateada,
                onValueChange = {},
                label = { Text("Fecha objetivo para tu meta") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { mostrarModalFecha = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
                    }
                }
            )

            // --- HORA PREFERIDA DE ESTUDIO ---
            OutlinedTextField(
                value = horaSeleccionada,
                onValueChange = {},
                label = { Text("Hora preferida de estudio") },
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { mostrarModalHora = true }) {
                        Icon(Icons.Default.Schedule, contentDescription = "Seleccionar hora")
                    }
                }
            )

            HorizontalDivider()

            // --- NIVEL DE PARTIDA (RadioButtons) ---
            Text(text = "Nivel de partida", style = MaterialTheme.typography.titleMedium)
            Column(modifier = Modifier.selectableGroup()) {
                opcionesNivel.chunked(2).forEach { filaNiveles ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        filaNiveles.forEach { nivel ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .weight(1f)
                                    .selectable(
                                        selected = (nivel == nivelSeleccionado),
                                        onClick = { nivelSeleccionado = nivel },
                                        role = Role.RadioButton
                                    )
                                    .padding(vertical = 4.dp)
                            ) {
                                RadioButton(
                                    selected = (nivel == nivelSeleccionado),
                                    onClick = null
                                )
                                Text(text = nivel, modifier = Modifier.padding(start = 8.dp))
                            }
                        }
                    }
                }
            }

            HorizontalDivider()

            // --- ÁREAS DE ESTUDIO A REFORZAR (Checkboxes) ---
            Text(text = "Áreas a reforzar", style = MaterialTheme.typography.titleMedium)
            Column {
                opcionesAreas.forEach { area ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(role = Role.Checkbox) {
                                areasSeleccionadas = if (areasSeleccionadas.contains(area)) {
                                    areasSeleccionadas - area
                                } else {
                                    areasSeleccionadas + area
                                }
                            }
                            .padding(vertical = 6.dp)
                    ) {
                        Checkbox(
                            checked = areasSeleccionadas.contains(area),
                            onCheckedChange = null
                        )
                        Text(text = area, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }

            HorizontalDivider()

            // --- SWITCH RECORDATORIOS ---
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(role = Role.Switch) { recordatoriosActivos = !recordatoriosActivos }
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Activar recordatorios de práctica",
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = recordatoriosActivos,
                    onCheckedChange = null
                )
            }

            // --- SWITCH TÉRMINOS Y CONDICIONES ---
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(role = Role.Switch) { terminosAceptados = !terminosAceptados }
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "Acepto los términos y condiciones",
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = terminosAceptados,
                    onCheckedChange = null
                )
            }

            // --- BOTÓN GUARDAR PERFIL ---
            Button(
                onClick = {
                    Toast.makeText(context, "¡Perfil guardado con éxito!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = terminosAceptados
            ) {
                Text("Guardar Perfil")
            }

            // Espacio extra al final para permitir hacer scroll completo
            Spacer(modifier = Modifier.height(100.dp))
        }
    }

    if (mostrarModalFecha) {
        DatePickerModalInput(
            onDateSelected = { fechaMilisegundos = it },
            onDismiss = { mostrarModalFecha = false }
        )
    }

    if (mostrarModalHora) {
        AlertDialog(
            onDismissRequest = { mostrarModalHora = false },
            confirmButton = {},
            text = {
                DialExample(
                    onConfirm = { hora, minuto ->
                        horaSeleccionada = String.format(Locale.getDefault(), "%02d:%02d", hora, minuto)
                        mostrarModalHora = false
                    },
                    onDismiss = { mostrarModalHora = false }
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialExample(onConfirm: (Int, Int) -> Unit, onDismiss: () -> Unit) {
    val timePickerState = rememberTimePickerState(
        initialHour = 12,
        initialMinute = 0,
        is24Hour = true,
    )

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TimePicker(state = timePickerState)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                onConfirm(timePickerState.hour, timePickerState.minute)
            }) {
                Text("OK")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModalInput(onDateSelected: (Long?) -> Unit, onDismiss: () -> Unit) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
