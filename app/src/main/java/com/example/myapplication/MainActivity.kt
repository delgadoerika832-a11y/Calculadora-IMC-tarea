package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aquí llamamos a nuestra función de pantalla
            PantallaUno()
        }
    }
}

@Composable
fun PantallaUno() {
    // Aquí van tus variables de estado
    var nombre by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("Ingresa tus datos") }

    // Y aquí va el diseño de la pantalla
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso (kg)") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = altura, onValueChange = { altura = it }, label = { Text("Altura (cm)") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            mensaje = "¡Hola $nombre! Datos recibidos."
        }) {
            Text("Guardar Datos")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = mensaje)
    }
}