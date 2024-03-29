package com.endava.uefatest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

internal class UefaViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    internal val uiState: StateFlow<UiState> = _uiState

    internal fun onUiEvent(event: UefaUiEvent) {
        when (event) {
            is UefaUiEvent.OpenTeamView -> _uiState.update { it.copy(currentScreen = Screens.Team, selectedTheme = event.theme) }
            is UefaUiEvent.GoBack -> _uiState.update { it.copy(currentScreen = Screens.Main, selectedTheme = Themes.Default) }
            is UefaUiEvent.SelectTab -> _uiState.update { it.copy(selectedTab = event.selectedTab) }
        }
    }

    internal data class UiState(
        val currentScreen: Screens = Screens.Main,
        val selectedTheme: Themes = Themes.Default,
        val selectedTab: Tab = Tab.Squad,
        val positionLists: List<PositionPlayers> = barcelonaTeam
    )

    internal sealed class UefaUiEvent {
        data class OpenTeamView(val theme: Themes) : UefaUiEvent()
        data object GoBack : UefaUiEvent()
        data class SelectTab(val selectedTab: Tab) : UefaUiEvent()
    }
}
