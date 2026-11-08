// ui/theme/Theme.kt
package kz.ln.racecalendar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NorrisGreen = Color(0xFF00D26A)
val NorrisLime = Color(0xFFC6FF00)
val AsphaltBlack = Color(0xFF0A0A0A)
val CarbonSurface = Color(0xFF151515)
val CarbonCard = Color(0xFF1E1E1E)

private val RaceColors = darkColorScheme(
    primary = NorrisGreen,
    secondary = NorrisLime,
    background = AsphaltBlack,
    surface = CarbonSurface,
    surfaceVariant = CarbonCard,
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun RaceCalendarTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = RaceColors, content = content)
}