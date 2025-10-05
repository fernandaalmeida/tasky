rerspackage com.falmeida.tasky.feature.agenda.presentation.mapper

import com.falmeida.tasky.core.domain.model.AgendaDay
import com.falmeida.tasky.feature.agenda.presentation.*
import java.time.format.DateTimeFormatter
import java.time.LocalTime

private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

fun AgendaDay.toUi() = com.falmeida.tasky.feature.agenda.presentation.AgendaUiState(
    date = this.date,
    isLoading = false,
    events = this.events.map { domainEvent ->
        EventUi(
            id = domainEvent.id,
            title = domainEvent.title,
            timeLabel = domainEvent.time.format(timeFormatter)
        )
    },
    tasks = this.tasks.map { domainTask ->
        TaskUi(
            id = domainTask.id,
            title = domainTask.title,
            timeLabel = domainTask.dateTime.toLocalTime().format(timeFormatter),
            isDone = domainTask.isDone
        )
    },
    reminders = this.reminders.map { domainRem ->
        ReminderUi(
            id = domainRem.id,
            title = domainRem.title,
            timeLabel = domainRem.dateTime.toLocalTime().format(timeFormatter)
        )
    }
)

