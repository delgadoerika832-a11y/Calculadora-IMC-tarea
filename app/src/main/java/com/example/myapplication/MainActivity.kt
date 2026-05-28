package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Este es el controlador que gestiona los "viajes" entre pantallas
            val navController = rememberNavController()

            // NavHost define qué pantallas existen en la app
            NavHost(navController = navController, startDestination = "pantalla_uno") {
                composable("pantalla_uno") { PantallaUno(navController) }
                composable("pantalla_dos") { PantallaDos(navController) }
            }
        }
    }
}

@Composable
fun PantallaUno(navController: androidx.navigation.NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Esta es la Pantalla 1")
        Button(onClick = { navController.navigate("pantalla_dos") }) {
            Text("Ir a Pantalla 2")
        }
    }
}

@Composable
fun PantallaDos(navController: androidx.navigation.NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("¡Bienvenida a la Pantalla 2!")
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver atrás")
        }
    }
}