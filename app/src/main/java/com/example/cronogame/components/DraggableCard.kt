package com.example.cronogame.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cronogame.R
import com.example.cronogame.models.HistoricalEvent

@Composable
fun DraggableCard(event: HistoricalEvent, onDropped: (HistoricalEvent) -> Unit) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        onDropped(event) // Soltar la tarjeta actual.
                        offsetX = 0f
                        offsetY = 0f
                    },
                    onDrag = { _, dragAmount ->
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                )
            }
            .background(Color.LightGray, shape = CircleShape)
            .padding(24.dp)
    ) {
        // Usa directamente los datos del evento.
        EventCard(
            categoryId = event.categoryId,
            eventName = event.eventName,
            year = event.year.toString()
        )
    }
}


@Composable
fun EventCard(
    categoryId: Int,
    eventName: String,
    year: String
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Categoría: $categoryId",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = eventName,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Año: $year",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDraggableCard() {
    val sampleEvent = HistoricalEvent(
        categoryId = 1,
        eventName = "Descubrimiento de América",
        year = 1492
    )
    DraggableCard(event = sampleEvent, onDropped = {})
}
