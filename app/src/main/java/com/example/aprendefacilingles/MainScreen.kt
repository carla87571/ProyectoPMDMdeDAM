package com.example.aprendefacilingles

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

// Rutas tipadas seguras
@Serializable
object Inicio

@Serializable
object Completar

@Serializable
data class Progreso(val contador: Int)

@Serializable
object Conversar

@Serializable
data class Perfil(val contador: Int)


// Destinos del menú inferior
enum class Destination(
    val icon: ImageVector,
    val label: String,
    val contentDescription: String
) {
    INICIO(Icons.Filled.Home, "Inicio", "Ir a inicio"),
    PERFIL(Icons.Filled.Person, "Perfil", "Ir a perfil"),
    COMPLETAR(Icons.Filled.Edit, "Completar", "Ir a completar"),
    PROGRESO(Icons.Filled.BarChart, "Progreso", "Ir a ver mi progreso"),
    CONVERSAR(Icons.AutoMirrored.Filled.Chat, "Conversar", "Ir a conversar")
}
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var contador by rememberSaveable { mutableIntStateOf(0) }
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
    var mostrarLoginDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Aprende Inglés Rápido") },
                actions = {
                    IconButton(onClick = { mostrarLoginDialog = true }) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Iniciar sesión"
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            selectedDestination = index
                            when (index) {
                                0 -> navController.navigate(Inicio) {
                                    popUpTo<Inicio> { inclusive = true }
                                    launchSingleTop = true
                                }
                                1 -> navController.navigate(Perfil(contador)) {
                                    popUpTo<Inicio>()
                                    launchSingleTop = true
                                }
                                2 -> navController.navigate(Completar) {
                                    popUpTo<Inicio>()
                                    launchSingleTop = true
                                }
                                3 -> navController.navigate(Progreso(contador)) {
                                    popUpTo<Inicio>()
                                    launchSingleTop = true
                                }
                                4 -> navController.navigate(Conversar) {
                                    popUpTo<Inicio>()
                                    launchSingleTop = true
                                }
                            }
                        },
                        icon = { Icon(destination.icon, contentDescription = destination.contentDescription) },
                        label = { Text(destination.label) }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                Toast.makeText(context, "Acción FAB pulsada", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Inicio,
            modifier = Modifier.padding(innerPadding).fillMaxSize()
        ) {
            composable<Inicio> {
                PantallaInicio(
                    contador = contador,
                    onIncrementar = { contador++ }
                )
            }
            composable<Perfil> { backStackEntry ->
                val args = backStackEntry.toRoute<Perfil>()
                PantallaPerfil(contador = args.contador)
            }
            composable<Completar> {
                PantallaCompletar()
            }
            composable<Progreso> { backStackEntry ->
                val args = backStackEntry.toRoute<Progreso>()
                PantallaProgreso(contador = args.contador)
            }
            composable<Conversar> {
                PantallaConversar()
            }
        }
    }

    if (mostrarLoginDialog) {
        PantallaLogin(onDismiss = { mostrarLoginDialog = false })
    }
}
