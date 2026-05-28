package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "pantalla_uno") {
                composable("pantalla_uno") { PantallaUno(navController) }

                // Aquí definimos que la pantalla dos recibe un parámetro llamado "nombre"
                composable(
                    "pantalla_dos/{nombre}",
                    arguments = listOf(navArgument("nombre") { type = NavType.StringType })
                ) { backStackEntry ->
                    val nombreRecibido = backStackEntry.arguments?.getString("nombre") ?: "Invitado"
                    PantallaDos(navController, nombreRecibido)
                }
            }
        }
    }
}

@Composable
fun PantallaUno(navController: androidx.navigation.NavController) {
    var nombre by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        Button(onClick = { navController.navigate("pantalla_dos/$nombre") }) {
            Text("Ir a Pantalla 2")
        }
    }
}

@Composable
fun PantallaDos(navController: androidx.navigation.NavController, nombre: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("¡Bienvenido, $nombre!")
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver atrás")
        }
    }
}