package com.example.aprendefacilingles

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PantallaConversar(onItemClick: (Int) -> Unit) {
    // Lista 1: Nivel A1 hasta Nivel A2
    val itemsNivelA = remember {
        listOf(
            CarouselItem(0, R.drawable.carrusel1, "Amistades", "Haciendo nuevas amistades"),
            CarouselItem(1, R.drawable.carrusel2, "Hamburguesas", "En un local de hamburguesas"),
            CarouselItem(2, R.drawable.carrusel3, "Cafetería", "En una Cafetería"),
            CarouselItem(3, R.drawable.carrusel4, "Tienda Nike", "En la tienda de Nike"),
            CarouselItem(4, R.drawable.carrusel5, "Restaurante", "En un restaurante")
        )
    }

    // Lista 2: Nivel B1 hasta Nivel B2
    val itemsNivelB = remember {
        listOf(
            CarouselItem(5, R.drawable.carrusel5, "Entrevista", "Entrevista de trabajo"),
            CarouselItem(6, R.drawable.carrusel4, "Proyectos", "Presentación de proyectos"),
            CarouselItem(7, R.drawable.carrusel3, "Reunión", "Reunión de negocios"),
            CarouselItem(8, R.drawable.carrusel2, "Viaje", "Viaje de estudios"),
            CarouselItem(9, R.drawable.carrusel1, "Conferencia", "Conferencia internacional")
        )
    }

    // Lista 3: Nivel C1 hasta Nivel C2
    val itemsNivelC = remember {
        listOf(
            CarouselItem(10, R.drawable.carrusel2, "Contratos", "Negociación de contratos"),
            CarouselItem(11, R.drawable.carrusel1, "Debates", "Debates académicos"),
            CarouselItem(12, R.drawable.carrusel4, "Redacción", "Redacción científica"),
            CarouselItem(13, R.drawable.carrusel5, "Liderazgo", "Discurso de liderazgo"),
            CarouselItem(14, R.drawable.carrusel3, "Análisis", "Análisis literario avanzado")
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
                .padding(top = 16.dp, bottom = 80.dp)
        ) {
            CarouselExample(
                title = "Conversaciones Nivel A1 - A2",
                carouselItems = itemsNivelA,
                onItemClick = onItemClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            CarouselExample(
                title = "Conversaciones Nivel B1 - B2",
                carouselItems = itemsNivelB,
                onItemClick = onItemClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            CarouselExample(
                title = "Conversaciones Nivel C1 - C2",
                carouselItems = itemsNivelC,
                onItemClick = onItemClick
            )
        }
    }
}
