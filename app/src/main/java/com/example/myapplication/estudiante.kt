package com.example.myapplication

// REQUERIMIENTO: Data Class con campo observaciones anulable (String?)
data class Estudiante(
    val id: Int,
    val nombre: String,
    val calificacion: Double,
    val observaciones: String? = null // Permite nulos [cite: 9]
)