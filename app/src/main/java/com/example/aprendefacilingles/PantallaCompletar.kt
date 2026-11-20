package com.example.aprendefacilingles

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class EjercicioCompletar(
    val id: Int,
    val fraseEspanol: String,
    val fraseInglesIncompleta: String,
    val respuestaCorrecta: String
)

@Composable
fun PantallaCompletar() {
    val context = LocalContext.current

    val ejercicios = remember {
        listOf(
            EjercicioCompletar(
                id = 1,
                fraseEspanol = "Ella es una estudiante.",
                fraseInglesIncompleta = "She is a ___.",
                respuestaCorrecta = "student"
            ),
            EjercicioCompletar(
                id = 2,
                fraseEspanol = "Me gusta beber agua fresca.",
                fraseInglesIncompleta = "I like to drink fresh ___.",
                respuestaCorrecta = "water"
            )
        )
    }

    var indiceActual by remember { mutableIntStateOf(0) }
    var respuestaUsuario by remember { mutableStateOf("") }

    val ejercicioActual = ejercicios[indiceActual]

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Completar Frases",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Ejercicio ${indiceActual + 1} de ${ejercicios.size}",
                style = MaterialTheme.typography.titleMedium
            )

            HorizontalDivider()

            // TARJETA 1: Frase en Español
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "🇪🇸 Frase en Español",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = ejercicioActual.fraseEspanol,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // TARJETA 2: Frase en Inglés + Campo de entrada de texto
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "🇬🇧 Completa en Inglés",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = ejercicioActual.fraseInglesIncompleta,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = respuestaUsuario,
                        onValueChange = { respuestaUsuario = it },
                        label = { Text("Escribe la palabra faltante") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // BOTÓN DE COMPROBAR
            Button(
                onClick = {
                    if (respuestaUsuario.trim().equals(ejercicioActual.respuestaCorrecta, ignoreCase = true)) {
                        Toast.makeText(context, "¡Correcto! 🎉", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Incorrecto. Inténtalo de nuevo 💡 (Respuesta: ${ejercicioActual.respuestaCorrecta})",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = respuestaUsuario.isNotBlank()
            ) {
                Text("Comprobar")
            }

            // BOTÓN SIGUIENTE EJERCICIO
            if (indiceActual < ejercicios.size - 1) {
                OutlinedButton(
                    onClick = {
                        indiceActual++
                        respuestaUsuario = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Siguiente ejercicio ➔")
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
