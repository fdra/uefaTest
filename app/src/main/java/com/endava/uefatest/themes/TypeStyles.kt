package com.endava.uefatest.themes

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

val LocalTeamTypeStyles = staticCompositionLocalOf {
    TeamTypeStyles(
        bigHeading = TextStyle.Default,
        status = TextStyle.Default,
        heading = TextStyle.Default,
        tab = TextStyle.Default,
        positionHeading = TextStyle.Default,
        playerName = TextStyle.Default,
        playerCountry = TextStyle.Default,
        playerAge = TextStyle.Default
    )
}

@Immutable
data class TeamTypeStyles(
    val bigHeading: TextStyle,
    val status: TextStyle,
    val heading: TextStyle,
    val tab: TextStyle,
    val positionHeading: TextStyle,
    val playerName: TextStyle,
    val playerCountry: TextStyle,
    val playerAge: TextStyle
)
