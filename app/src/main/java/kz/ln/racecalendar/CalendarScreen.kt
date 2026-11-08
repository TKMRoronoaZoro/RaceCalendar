package kz.ln.racecalendar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.ln.racecalendar.model.MockData
import kz.ln.racecalendar.model.Race
import kz.ln.racecalendar.model.Series
import kz.ln.racecalendar.model.*
import java.time.format.DateTimeFormatter

@Composable
fun CalendarScreen() {
    var filter by remember { mutableStateOf<Series?>(null) }
    val races = remember(filter) {
        MockData.races.filter { filter == null || it.series == filter }
    }

    Column(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Text(
            "Ближайшие гонки",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 16.dp),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(selected = filter == null, onClick = { filter = null },
                label = { Text("Все") })
            Series.entries.forEach { s ->
                FilterChip(selected = filter == s, onClick = { filter = s },
                    label = { Text(s.title) })
            }
        }
        Spacer(Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(races, key = { it.id }) { RaceCard(it) }
        }
    }
}

@Composable
fun RaceCard(race: Race) {
    val fmt = DateTimeFormatter.ofPattern("d MMM, HH:mm")
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                race.series.title.uppercase(),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(race.name, style = MaterialTheme.typography.titleLarge)
            Text("${race.circuit}, ${race.country}",
                style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(8.dp))
            Text(race.start.format(fmt),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium)
        }
    }
}