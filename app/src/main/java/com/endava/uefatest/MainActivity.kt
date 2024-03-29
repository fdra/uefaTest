package com.endava.uefatest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.endava.uefatest.composables.MainScreen
import com.endava.uefatest.composables.TeamScreen
import com.endava.uefatest.themes.TeamTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val uefaVM: UefaViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val uiState by uefaVM.uiState.collectAsState()

            TeamTheme(theme = uiState.selectedTheme) {
                SelectScreen(
                    uiState = uiState,
                    onUiEvent = {
                        uefaVM.onUiEvent(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun SelectScreen(
    uiState: UefaViewModel.UiState,
    onUiEvent: (UefaViewModel.UefaUiEvent) -> Unit
) {
    when(uiState.currentScreen) {
        Screens.Main -> {
            SetStatusBarColor(true)
            MainScreen (onUiEvent)
        }
        Screens.Team -> {
            SetStatusBarColor(false)
            TeamScreen(
                selectedTab = uiState.selectedTab,
                positionLists = uiState.positionLists,
                onUiEvent = onUiEvent
            )
        }
    }
}

@Composable
fun SetStatusBarColor(darkIcons: Boolean) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = darkIcons
        )
    }
}
