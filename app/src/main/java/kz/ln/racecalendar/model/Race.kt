package kz.ln.racecalendar.model

import java.time.LocalDateTime

enum class Series(val title: String) { F1("Formula 1"), MOTOGP("MotoGP") }

data class Race(
    val id: String,
    val series: Series,
    val name: String,
    val circuit: String,
    val country: String,
    val start: LocalDateTime,
)

object MockData {
    val races = listOf(
        Race("f1-1", Series.F1, "Гран-при Абу-Даби", "Яс-Марина", "ОАЭ",
            LocalDateTime.of(2026, 12, 6, 16, 0)),
        Race("mgp-1", Series.MOTOGP, "Гран-при Валенсии", "Рикардо Тормо", "Испания",
            LocalDateTime.of(2026, 11, 15, 14, 0)),
    ).sortedBy { it.start }
}