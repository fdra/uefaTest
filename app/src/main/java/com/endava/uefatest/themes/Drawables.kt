package com.endava.uefatest.themes

import androidx.compose.runtime.staticCompositionLocalOf
import com.endava.uefatest.R

data class TeamDrawables(val headerBackground: Int)

val LocalTeamDrawables = staticCompositionLocalOf {
    TeamDrawables(
        headerBackground = R.drawable.ucl_header
    )
}
