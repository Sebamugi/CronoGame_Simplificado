package com.example.cronogame.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

    val allEvents = InitialData.events.filter { it.categoryId == categoryId }
    var eventsList by remember { mutableStateOf(allEvents.toMutableList()) }
    var timeline by remember { mutableStateOf(mutableListOf<HistoricalEvent>()) }
    var attemptsLeft by remember { mutableStateOf(3) } // Número de intentos disponibles
    var correctAnswers by remember { mutableStateOf(0) }

    Scaffold(
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (eventsList.isNotEmpty() && attemptsLeft > 0) {
                    DraggableCard(
                        event = eventsList.first(),
                        onDropped = { droppedEvent ->
                            if (timeline.isEmpty() || droppedEvent.year >= timeline.last().year) {
                                timeline = timeline.toMutableList().apply { add(droppedEvent) } // Provoca recomposición
                                correctAnswers++
                            } else {
                                attemptsLeft--
                            }
                            eventsList = eventsList.drop(1).toMutableList()
                        }
                    )

                } else {
                    // Redirigir a la pantalla de resultados
                    navController.navigate("result/$correctAnswers")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Intentos restantes: $attemptsLeft",
                    style = MaterialTheme.typography.titleMedium
                )

                TimelineRow(
                    timeline = timeline
                )
            }
        }
    )
}