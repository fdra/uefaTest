package com.endava.uefatest.themes

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val white = Color(0xFFFFFFFF)
internal val black = Color(0xFF000000)

internal val tabsBackgroundBlue = Color(0xFF151573)
internal val tabSelectedBlue = Color(0xFF5AF7DC)
internal val backgroundBlue = Color(0xFF0A0A61)
internal val dividerBlue = Color(0xFF000040)

internal val tabsBackgroundDark = Color(0xFF2C2C2E)
internal val tabSelectedDark = Color(0xFFFF6900)
internal val backgroundDark = Color(0xFF1C1C1E)
internal val dividerDark = black

val LocalTeamColors = staticCompositionLocalOf {
    TeamColors(
        tabsBackground = Color.Unspecified,
        selectedTab = Color.Unspecified,
        positionBackground = Color.Unspecified,
        positionDivider = Color.Unspecified,
        playerDivider = Color.Unspecified,
        textColor = Color.Unspecified
    )
}

@Immutable
data class TeamColors(
    val tabsBackground: Color,
    val selectedTab: Color,
    val positionBackground: Color,
    val positionDivider: Color,
    val playerDivider: Color,
    val textColor: Color
)
