package com.example.cronogame.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cronogame.components.DraggableCard
import com.example.cronogame.components.TimelineRow
import com.example.cronogame.models.HistoricalEvent
import com.example.cronogame.models.InitialData
import com.example.cronogame.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(navController: NavController, categoryId: Int) {
    val category = InitialData.categories.find { it.id == categoryId }
    val allEvents = InitialData.events.filter { it.categoryId == categoryId }

    var attemptsLeft by remember { mutableStateOf(3) }
    var eventsList by remember { mutableStateOf(allEvents) }
    var timeline by remember { mutableStateOf(mutableListOf<HistoricalEvent>()) }

    fun handleInvalidTimeline() {
        attemptsLeft -= 1
        if (attemptsLeft <= 0) {
            navController.navigate(AppScreens.ResultScreen.route) {
                popUpTo(AppScreens.GameScreen.route) { inclusive = true }
            }
        }
    }

    fun onEventDropped(event: HistoricalEvent) {
        timeline.add(event)
        eventsList = eventsList.drop(1) // Eliminar el evento de la lista
        validateTimeline(timeline) { isValid ->
            if (!isValid) handleInvalidTimeline()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = category?.name ?: "Sin categoría",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (eventsList.isEmpty()) {
                    Text("No hay eventos disponibles para esta categoría.")
                } else {
                    DraggableCard(
                        event = eventsList.first(),
                        onDropped = { event -> onEventDropped(event) }
                    )
                }

                TimelineRow(timeline = timeline)

                Text(
                    text = "INTENTOS RESTANTES: $attemptsLeft",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    )
}

fun validateTimeline(timeline: MutableList<HistoricalEvent>, onValidationComplete: (Boolean) -> Unit) {
    val isValid = timeline.zipWithNext().all { (firstEvent, secondEvent) ->
        firstEvent.year <= secondEvent.year
    }
    onValidationComplete(isValid)
}