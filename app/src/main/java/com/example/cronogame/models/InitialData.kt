package com.example.cronogame.models

object InitialData {

    val categories = listOf(
        Category(name = "Historia general", id = 1)
    )

    val events = listOf(
        HistoricalEvent(
            categoryId = 1,
            eventName = "Descubrimiento de América",
            year = 1492
        ),
        HistoricalEvent(
            categoryId = 1,
            eventName = "Revolución Francesa",
            year = 1789
        ),
        HistoricalEvent(
            categoryId = 1,
            eventName = "Primera Guerra Mundial",
            year = 1914
        )
    )
}