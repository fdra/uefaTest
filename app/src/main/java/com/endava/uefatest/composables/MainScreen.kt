package com.endava.uefatest.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.endava.uefatest.Themes
import com.endava.uefatest.UefaViewModel

@Composable
internal fun MainScreen(
    onClickEvent: (UefaViewModel.UefaUiEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(
                onClick = { onClickEvent.invoke(UefaViewModel.UefaUiEvent.OpenTeamView(Themes.UCL)) })
            {
                Text(text = "UCL")
            }

            TextButton(onClick = {
                onClickEvent.invoke(UefaViewModel.UefaUiEvent.OpenTeamView(Themes.UEL))
            }) {
                Text(text = "UEL")
            }
        }
    }
}
