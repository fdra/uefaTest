package com.endava.uefatest.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.uefatest.PlayerData
import com.endava.uefatest.PositionPlayers
import com.endava.uefatest.Themes
import com.endava.uefatest.barcelonaTeam
import com.endava.uefatest.themes.TeamTheme

@Composable
internal fun SquadView(
    modifier: Modifier = Modifier,
    positionLists: List<PositionPlayers>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(TeamTheme.teamColors.positionBackground)
    ) {
        itemsIndexed(positionLists) { index, positionPlayers ->
            PositionListView(
                modifier = Modifier.padding(16.dp),
                positionTitle = positionPlayers.positionName,
                players = positionPlayers.players
            )
            if(index != positionLists.size - 1) {
                Divider(
                    color = TeamTheme.teamColors.positionDivider,
                    thickness = 8.dp
                )
            }
        }
        item {
            Spacer(modifier = (Modifier.windowInsetsBottomHeight(WindowInsets.systemBars)))
        }
    }
}

@Composable
private fun PositionListView(
    modifier: Modifier,
    positionTitle: String,
    players: List<PlayerData>
) {
    Column(modifier = modifier) {
        Text(
            text = positionTitle,
            style = TeamTheme.teamTypeStyles.positionHeading
        )
        Spacer(modifier = Modifier.height(16.dp))

        players.forEachIndexed { index, playerData ->
            PlayerItem(
                modifier = Modifier.padding(vertical = 8.dp),
                playerData = playerData
            )
            if(index != players.size - 1) {
                Divider(thickness = 1.dp, color = TeamTheme.teamColors.playerDivider.copy(alpha = 0.1f))
            }
        }
    }
}

@Preview
@Composable
private fun  SquadViewPreview() {
    TeamTheme(theme = Themes.UCL) {
        SquadView(positionLists = barcelonaTeam)
    }
}

