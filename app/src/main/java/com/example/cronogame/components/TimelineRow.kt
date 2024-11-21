package com.example.cronogame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.cronogame.models.HistoricalEvent

@Composable
fun TimelineRow(
    timeline: List<HistoricalEvent>,
    onEventAdded: (HistoricalEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFE0E0E0)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        timeline.forEach { event ->
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFF6A1B9A), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = event.year.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )
            }
        }
    }
}
