package kz.ln.racecalendar.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.*
import androidx.compose.ui.graphics.Color

enum class Tab(val route: String, val label: String, val icon: ImageVector) {
    Calendar("calendar", "Календарь", Icons.Filled.CalendarMonth),
    Standings("standings", "Чемпионат", Icons.Filled.EmojiEvents),
    Results("results", "Результаты", Icons.Filled.Flag),
}

@Composable
fun AppRoot() {
    val nav = rememberNavController()
    val backStack by nav.currentBackStackEntryAsState()
    val current = backStack?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
                Tab.entries.forEach { tab ->
                    NavigationBarItem(
                        selected = current == tab.route,
                        onClick = {
                            nav.navigate(tab.route) {
                                popUpTo(nav.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                        ),
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = Tab.Calendar.route,
            modifier = Modifier.padding(padding),
        ) {
            composable(Tab.Calendar.route) { CalendarScreen() }
            composable(Tab.Standings.route) { Text("Standings: скоро") }
            composable(Tab.Results.route) { Text("Results: скоро") }
        }
    }
}