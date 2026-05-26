package com.example.myapplication

import org.junit.Test

class ExampleUnitTest {

    @Test
    fun ejecutarLogicaEstudiantes() {
        // REQUERIMIENTO: Uso de val para inmutables [cite: 7]
        val lista = listOf(
            Estudiante(1, "Juan", 9.5, "Excelente trabajo"),
            Estudiante(2, "Ana", 5.0, null), // Caso nulo para probar Null Safety [cite: 11]
            Estudiante(3, "Pedro", 7.5, null)
        )

        println("SISTEMA DE: ${Configuracion.NOMBRE_INSTITUCION}")

        // REQUERIMIENTO: Bucle for para iterar [cite: 15]
        for (estudiante in lista) {
            procesarDatos(estudiante)
        }
    }

    // REQUERIMIENTO: Función con parámetros por defecto [cite: 17]
    fun procesarDatos(estudiante: Estudiante, mostrarDetalle: Boolean = true) {

        // REQUERIMIENTO: when como expresión [cite: 13]
        val estado = when (estudiante.calificacion) {
            in 0.0..6.9 -> "Reprobado"
            in 7.0..8.9 -> "Bueno"
            in 9.0..10.0 -> "Excelente"
            else -> "Nota no válida"
        }

        // REQUERIMIENTO: Llamada segura (?.) y Operador Elvis (?:) [cite: 10, 11]
        val obs = estudiante.observaciones?.uppercase() ?: "Sin observaciones"

        println("ID: ${estudiante.id} | Nombre: ${estudiante.nombre}")
        println("Estado: $estado | Nota: ${estudiante.calificacion}")
        println("Observaciones: $obs")
        println("-------------------------------------------")
    }
}