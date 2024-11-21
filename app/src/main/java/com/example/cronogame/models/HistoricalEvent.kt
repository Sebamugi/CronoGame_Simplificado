package com.example.cronogame.models

data class HistoricalEvent(
    val categoryId: Int,  // ID de la categoría a la que pertenece el evento
    val eventName: String,  // Nombre del evento
    val year: Int  // Año en que ocurrió el evento
)