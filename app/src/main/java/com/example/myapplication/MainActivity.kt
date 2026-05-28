package com.example.myapplication // Asegúrate de que esta línea coincida con la de tu proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                composable(
                    "resultado/{nombre}/{imc}",
                    arguments = listOf(
                        navArgument("nombre") { type = NavType.StringType },
                        navArgument("imc") { type = NavType.FloatType }
                    )
                ) { backStackEntry ->
                    val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
                    val imc = backStackEntry.arguments?.getFloat("imc") ?: 0f
                    PantallaDos(navController, nombre, imc)
                }
            }
        }
    }
}

@Composable
fun PantallaUno(navController: androidx.navigation.NavController) {
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.Center) {
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso (kg)") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = altura, onValueChange = { altura = it }, label = { Text("Altura (m)") }, modifier = Modifier.fillMaxWidth())

        if (mostrarError) {
            Text("Por favor, ingresa valores válidos", color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val p = peso.toFloatOrNull()
            val a = altura.toFloatOrNull()
            if (p != null && a != null && p > 0 && a > 0) {
                val imc = p / (a * a)
                navController.navigate("resultado/$nombre/$imc")
            } else {
                mostrarError = true
            }
        }) { Text("Calcular IMC") }
    }
}

@Composable
fun PantallaDos(navController: androidx.navigation.NavController, nombre: String, imc: Float) {
    val categoria = when {
        imc < 18.5 -> "Bajo peso"
        imc < 25 -> "Peso normal"
        imc < 30 -> "Sobrepeso"
        else -> "Obesidad"
    }

    val colorCategoria = when (categoria) {
        "Bajo peso" -> Color.Red
        "Peso normal" -> Color(0xFF008000) // Verde
        "Sobrepeso" -> Color(0xFFFFA500)   // Naranja
        else -> Color.Red                  // Obesidad (Rojo)
    }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Hola $nombre, tu resultado es:")
        Text("IMC: %.1f".format(imc), style = MaterialTheme.typography.headlineLarge)
        Text(categoria, color = colorCategoria, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) { Text("Volver") }
    }
}