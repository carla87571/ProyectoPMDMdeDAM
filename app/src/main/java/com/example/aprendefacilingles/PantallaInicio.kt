package com.example.aprendefacilingles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun PantallaInicio(contador: Int, onIncrementar: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido a Aprende Inglés Rápido!",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Puntos acumulados: $contador",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Selecciona un nivel para practicar:",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Niveles A1 y A2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NivelBox(nivel = "Nivel A1", descripcion = "Básico", modifier = Modifier.weight(1f), onClick = onIncrementar)
                NivelBox(nivel = "Nivel A2", descripcion = "Elemental", modifier = Modifier.weight(1f), onClick = onIncrementar)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Niveles B1 y B2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NivelBox(nivel = "Nivel B1", descripcion = "Intermedio", modifier = Modifier.weight(1f), onClick = onIncrementar)
                NivelBox(nivel = "Nivel B2", descripcion = "Intermedio Alto", modifier = Modifier.weight(1f), onClick = onIncrementar)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Niveles C1 y C2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NivelBox(nivel = "Nivel C1", descripcion = "Avanzado", modifier = Modifier.weight(1f), onClick = onIncrementar)
                NivelBox(nivel = "Nivel C2", descripcion = "Maestría", modifier = Modifier.weight(1f), onClick = onIncrementar)
            }
        }
    }
}

@Composable
fun NivelBox(nivel: String, descripcion: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = nivel,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = descripcion,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}
