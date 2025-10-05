package com.falmeida.tasky.feature.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
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
        // In a real setup, this could handle ViewModel states and pass down UI state + actions
    }
}

@Composable
fun AgendaScreen(
    agendaState: AgendaUiState,
    onAddEventClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val isTablet = configuration.screenWidthDp >= 600

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = if (isTablet) 48.dp else 0.dp)
            ) {
                AgendaHeader(currentDate = agendaState.selectedDate)
                Spacer(modifier = Modifier.height(16.dp))
                EventList(events = agendaState.events)
            }

            TaskyFloatingActionButton(
                onClick = onAddEventClick,
                contentDescription = "Add new event",
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
            )
        }
    }
}

@Composable
fun AgendaHeader(currentDate: LocalDate) {
    Column(modifier = Modifier.padding(start = 24.dp, top = 24.dp)) {
        Text(
            text = currentDate.month.name.uppercase(),
            style = TaskyTypography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
        Text(
            text = "Today",
            style = TaskyTypography.headlineLarge.copy(fontSize = 32.sp),
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EventList(events: List<AgendaUiState>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .semantics {
                contentDescription = "Agenda event list"
            }
    ) {
        items(events) { event ->
            EventCard(event)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun EventCard(event: AgendaEvent) {
    val backgroundColor = when (event.type) {
        EventType.PROJECT -> TaskyGreen
        EventType.MEETING -> TaskyYellow
        EventType.BREAK -> TaskyGray
    }

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = backgroundColor,
        modifier = Modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = "${event.title}, ${event.description}, at ${event.time}"
                role = Role.Button
            },
        tonalElevation = 2.dp
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
                color = TaskyBlack.copy(alpha = 0.8f)
            )
            Text(
                text = "${event.date}, ${event.time}",
                style = TaskyTypography.labelSmall,
                color = TaskyBlack.copy(alpha = 0.6f)
            )
        }
    }
}