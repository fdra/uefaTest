package com.endava.uefatest.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.endava.uefatest.PlayerData
import com.endava.uefatest.R
import com.endava.uefatest.Themes
import com.endava.uefatest.themes.TeamTheme
import com.endava.uefatest.themes.backgroundBlue

@Composable
internal fun PlayerItem(
    modifier: Modifier = Modifier,
    playerData: PlayerData
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            PlayerImage(image = playerData.playerImage)
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(
                    text = playerData.name,
                    style = TeamTheme.teamTypeStyles.playerName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = playerData.country,
                    style = TeamTheme.teamTypeStyles.playerCountry,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Text(
            text = "${playerData.age}",
            style = TeamTheme.teamTypeStyles.playerAge
        )
    }
}

@Composable
private fun PlayerImage(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.messi
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        painter = painterResource(id = image),
        contentDescription = "PlayerImage",
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}

@Preview
@Composable
private fun  PlayerItemPreview() {
    val playerData = PlayerData()
    TeamTheme(theme = Themes.UEL) {
        PlayerItem(
            modifier = Modifier.background(backgroundBlue),
            playerData = playerData
        )
    }
}
