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
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pantalla_uno") {
                    composable("pantalla_uno") { PantallaUno(navController) }
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
}

@Composable
fun PantallaUno(navController: androidx.navigation.NavController) {
    var nombre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Bienvenido a mi App", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Escribe tu nombre") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (nombre.isNotBlank()) {
                            navController.navigate("pantalla_dos/$nombre")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir a Pantalla 2")
                }
            }
        }
    }
}

@Composable
fun PantallaDos(navController: androidx.navigation.NavController, nombre: String) {
    Column(modifier = Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("¡Bienvenido, $nombre!", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver atrás")
        }
    }
}