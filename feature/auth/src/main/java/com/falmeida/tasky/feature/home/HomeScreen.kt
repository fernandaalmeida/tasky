package com.falmeida.tasky.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.falmeida.tasky.designsystem.component.theme.*
import java.time.LocalDate
import java.time.LocalTime

class AgendaScreenWrapper(
    private val onShowError: (String) -> Unit
) {
    @Composable
    fun AgendaScreen() {
    }
}

@Composable
fun AgendaScreen(
    agendaState: AgendaState,
    onAddEventClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            AgendaHeader(currentDate = agendaState.selectedDate)
            Spacer(modifier = Modifier.height(16.dp))
            EventList(events = agendaState.events)
        }

        FloatingActionButton(
            onClick = onAddEventClick,

            containerColor = MaterialTheme.colorScheme.fabContainer,
            contentColor = MaterialTheme.colorScheme.fabContent,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
fun AgendaHeader(currentDate: LocalDate) {
    Column(modifier = Modifier.padding(start = 24.dp, top = 24.dp)) {
        Text(
            text = currentDate.month.name.uppercase(),
            style = TaskyTypography.labelSmall,
            color = TaskyWhite.copy(alpha = 0.6f)
        )
        Text(
            text = "Today",
            style = TaskyTypography.headlineLarge.copy(fontSize = 32.sp),
            color = TaskyWhite,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EventList(events: List<AgendaEvent>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        items(events) { event ->
            EventCard(event)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun EventCard(event: AgendaEvent) {
    val background = when (event.type) {
        EventType.PROJECT -> TaskyGreen
        EventType.MEETING -> TaskyYellow
        EventType.BREAK -> TaskyGray
    }

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = background,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = event.title,
                style = TaskyTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = TaskyBlack
            )
            Text(
                text = event.description,
                style = TaskyTypography.bodyMedium,
                color = TaskyBlack.copy(alpha = 0.7f)
            )
            Text(
                text = "${event.date}, ${event.time}",
                style = TaskyTypography.labelSmall,
                color = TaskyBlack.copy(alpha = 0.6f)
            )
        }
    }
}

// Data classes for UI state

data class AgendaState(
    val selectedDate: LocalDate = LocalDate.now(),
    val events: List<AgendaEvent> = emptyList()
)

data class AgendaEvent(
    val id: String,
    val title: String,
    val description: String,
    val date: LocalDate,
    val time: LocalTime,
    val type: EventType
)

enum class EventType {
    PROJECT, MEETING, BREAK
}
