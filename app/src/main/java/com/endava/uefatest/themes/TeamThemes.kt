package com.endava.uefatest.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.endava.uefatest.R
import com.endava.uefatest.Themes

@Composable
fun TeamTheme(
    theme: Themes = Themes.Default,
    content: @Composable () -> Unit
) {
    val teamColors = getTeamColors(theme)

    val teamDrawables = if (theme == Themes.UEL) {
        TeamDrawables(R.drawable.uel_header)
    } else {
        TeamDrawables(R.drawable.ucl_header)
    }

    val teamTypeStyles = TeamTypeStyles(
        bigHeading = TextStyle(
            fontSize = 48.sp,
            letterSpacing = (-1.2).sp,
            lineHeight = 48.sp,
            color = teamColors.textColor,
            fontWeight = FontWeight.Bold
        ),
        status = TextStyle(
            fontSize = 14.sp,
            lineHeight = 18.sp,
            color = teamColors.textColor.copy(alpha = 0.7f)
        ),
        heading = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = teamColors.textColor
        ),
        tab = TextStyle(
            fontSize = 14.sp,
            lineHeight = 18.sp,
            color = teamColors.textColor
        ),
        positionHeading = TextStyle(
            fontSize = 20.sp,
            lineHeight = 22.sp,
            color = teamColors.textColor,
            fontWeight = FontWeight.Bold
        ),
        playerName = TextStyle(
            fontSize = 16.sp,
            lineHeight = 18.sp,
            color = teamColors.textColor
        ),
        playerCountry = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            color = teamColors.textColor.copy(alpha = 0.7f)
        ),
        playerAge = TextStyle(
            fontSize = 20.sp,
            lineHeight = 26.sp,
            fontWeight = FontWeight.Bold,
            color = teamColors.textColor
        )
    )

    CompositionLocalProvider(
        LocalTeamColors provides teamColors,
        LocalTeamTypeStyles provides teamTypeStyles,
        LocalTeamDrawables provides teamDrawables
    ) {
        content()
    }
}

private fun getTeamColors(
    theme: Themes
) : TeamColors {
    return when (theme) {
        Themes.UCL, Themes.Default -> TeamColors(
            tabsBackground = tabsBackgroundBlue,
            selectedTab = tabSelectedBlue,
            positionBackground = backgroundBlue,
            positionDivider = dividerBlue,
            playerDivider = white,
            textColor = white,
        )
        Themes.UEL -> TeamColors(
            tabsBackground = tabsBackgroundDark,
            selectedTab = tabSelectedDark,
            positionBackground = backgroundDark,
            positionDivider = dividerDark,
            playerDivider = white,
            textColor = white,
        )
    }
}

object TeamTheme {
    val teamColors: TeamColors
        @Composable
        get() = LocalTeamColors.current

    val teamTypeStyles: TeamTypeStyles
        @Composable
        get() = LocalTeamTypeStyles.current

    val teamDrawables: TeamDrawables
        @Composable
        get() = LocalTeamDrawables.current
}

