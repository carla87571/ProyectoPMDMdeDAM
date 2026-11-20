package com.example.aprendefacilingles

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class TurnoRoleplay(
    val hablante: String,
    val textoIngles: String,
    val traduccionEspanol: String,
    val esUsuario: Boolean
)

@Composable
fun PantallaDetalle(
    itemId: Int,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current

    val tituloSituacion = if (itemId == 0) "Hacer nuevas amistades" else "En un local de hamburguesas"

    val turnosConversacion = if (itemId == 0) {
        listOf(
            TurnoRoleplay(
                hablante = "Amy (Amiga)",
                textoIngles = "Hi! Welcome to my birthday party! I'm Amy.",
                traduccionEspanol = "¡Hola! ¡Bienvenido a mi fiesta de cumpleaños! Soy Amy.",
                esUsuario = false
            ),
            TurnoRoleplay(
                hablante = "Tú (Tu turno)",
                textoIngles = "Hi Amy! Happy birthday! I'm glad to be here.",
                traduccionEspanol = "¡Hola Amy! ¡Feliz cumpleaños! Me alegra estar aquí.",
                esUsuario = true
            ),
            TurnoRoleplay(
                hablante = "Amy (Amiga)",
                textoIngles = "Thank you so much! Let me introduce you to my friend Sarah.",
                traduccionEspanol = "¡Muchas gracias! Déjame presentarte a mi amiga Sarah.",
                esUsuario = false
            )
        )
    } else {
        listOf(
            TurnoRoleplay(
                hablante = "Cajero",
                textoIngles = "Hello! Welcome to Burger Express. What would you like to order today?",
                traduccionEspanol = "¡Hola! Bienvenido a Burger Express. ¿Qué te gustaría pedir hoy?",
                esUsuario = false
            ),
            TurnoRoleplay(
                hablante = "Tú (Tu turno)",
                textoIngles = "Hi! I'd like a double cheeseburger with fries, please.",
                traduccionEspanol = "¡Hola! Me gustaría una hamburguesa doble con queso y patatas, por favor.",
                esUsuario = true
            ),
            TurnoRoleplay(
                hablante = "Cajero",
                textoIngles = "Great choice! Would you like a drink with your combo?",
                traduccionEspanol = "¡Gran elección! ¿Deseas una bebida con tu combo?",
                esUsuario = false
            )
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cabecera con Flecha de Atrás y "Juego de roles"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver atrás"
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Juego de roles",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            // Título de la Situación
            Text(
                text = tituloSituacion,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Escucha y practica el diálogo interactivo:",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )

            HorizontalDivider()

            // Bocadillos de diálogo (Roleplay)
            turnosConversacion.forEach { turno ->
                val containerColor = if (turno.esUsuario) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surfaceVariant
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = containerColor)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = turno.hablante,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "\"${turno.textoIngles}\"",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Iconos de Sonido y Traducción
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            IconButton(onClick = {
                                Toast.makeText(
                                    context,
                                    "🔊 Reproduciendo: \"${turno.textoIngles}\"",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.VolumeUp,
                                    contentDescription = "Escuchar audio",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            IconButton(onClick = {
                                Toast.makeText(
                                    context,
                                    "🌐 Traducción: \"${turno.traduccionEspanol}\"",
                                    Toast.LENGTH_LONG
                                ).show()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Translate,
                                    contentDescription = "Ver traducción",
                                    tint = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
