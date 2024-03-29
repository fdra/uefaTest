package com.endava.uefatest
enum class Screens{
    Main,
    Team
}

enum class Themes{
    Default,
    UCL,
    UEL
}

data class PlayerData(
    val playerId: Long = 0,
    val name: String = "Player name",
    val country: String = "Country",
    val age: Int = 24,
    val playerImage: Int = R.drawable.messi
)

data class PositionPlayers(
    val positionName: String,
    val players: List<PlayerData>
)

enum class Tab {
    Overview,
    Matches,
    Groups,
    Stats,
    Squad
}

val barcelonaTeam = listOf(
    getPlayers("Goalkeepers"),
    getPlayers("Defenders"),
    getPlayers("Midfielders"),
    getPlayers("Forwards"),
    getPlayers("Coach"),
)

private fun getPlayers( positionName: String) = PositionPlayers (
    positionName,
    when (positionName) {
        "Coach" -> listOf(PlayerData())
        else -> listOf(
            PlayerData(),
            PlayerData(),
            PlayerData(),
            PlayerData()
        )
    }
)
