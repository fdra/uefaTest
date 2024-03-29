package com.endava.uefatest.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.endava.uefatest.PositionPlayers
import com.endava.uefatest.R
import com.endava.uefatest.Tab
import com.endava.uefatest.Themes
import com.endava.uefatest.UefaViewModel
import com.endava.uefatest.barcelonaTeam
import com.endava.uefatest.themes.TeamTheme
import com.endava.uefatest.themes.white
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
internal fun TeamScreen(
    selectedTab: Tab,
    positionLists: List<PositionPlayers>,
    onUiEvent: (UefaViewModel.UefaUiEvent) -> Unit
) {
    val state = rememberCollapsingToolbarScaffoldState()

    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .paint(
                        painter = painterResource(id = TeamTheme.teamDrawables.headerBackground),
                        contentScale = ContentScale.FillWidth
                    )
                    .pin()
            )

            HeaderView(modifier = Modifier.padding(top = 44.dp))

            TopBar(
                modifier = Modifier.pin(),
                onBackClick = { onUiEvent.invoke(UefaViewModel.UefaUiEvent.GoBack) }
            )
        }
    ) {
        when (selectedTab) {
            Tab.Squad -> SquadView(
                modifier = Modifier.padding(top = 44.dp),
                positionLists = positionLists
            )
            else -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(TeamTheme.teamColors.positionBackground)
            )
        }

        TabsView(
            tabs = Tab.entries,
            selectedTab = selectedTab,
            onTabClick = { onUiEvent.invoke(UefaViewModel.UefaUiEvent.SelectTab(it)) }
        )
    }
}


@Composable
internal fun HeaderView(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Barcelona",
            style = TeamTheme.teamTypeStyles.bigHeading,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = "Playing",
                    style = TeamTheme.teamTypeStyles.status,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "Round of 16",
                    style = TeamTheme.teamTypeStyles.heading,
                    color = TeamTheme.teamColors.textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Image(
                modifier = Modifier.size(110.dp),
                painter = painterResource(id = R.drawable.barcelona),
                contentDescription = "Team Badge",
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
internal fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val systemBarPaddings = WindowInsets.systemBars.asPaddingValues()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = systemBarPaddings.calculateTopPadding(),
                bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = onBackClick),
            painter = painterResource(id = R.drawable.ic_back),
            tint = white,
            contentDescription = "Back button"
        )
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_action),
            tint = white,
            contentDescription = "Action icon"
        )
    }
}

@Composable
internal fun TabsView(
    tabs: List<Tab>,
    selectedTab: Tab,
    onTabClick: (Tab) -> Unit = {}
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(TeamTheme.teamColors.tabsBackground),
        verticalAlignment = Alignment.CenterVertically,
        contentPadding = PaddingValues(horizontal = 5.dp)
    ) {
        items(tabs) { tab ->
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clickable { onTabClick(tab) }
            ) {
                val color = if (tab == selectedTab) TeamTheme.teamColors.selectedTab else TeamTheme.teamColors.textColor
                val modifier = if (tab == selectedTab) {
                    Modifier
                        .drawBehind {
                            val strokeWidthPx = 3.dp.toPx()
                            val verticalOffset = size.height + 10.sp.toPx()
                            drawLine(
                                color = color,
                                strokeWidth = strokeWidthPx,
                                start = Offset(0f, verticalOffset),
                                end = Offset(size.width, verticalOffset)
                            )
                        }
                } else {
                    Modifier
                }
                Text(
                    modifier = modifier,
                    text = tab.name,
                    style = TeamTheme.teamTypeStyles.tab,
                    color = color,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
private fun  TeamScreenUCLPreview() {
    TeamTheme(theme = Themes.UCL) {
        TeamScreen(selectedTab = Tab.Squad, positionLists = barcelonaTeam) {}
    }
}

@Preview
@Composable
private fun  TeamScreenUELPreview() {
    TeamTheme(theme = Themes.UEL) {
        TeamScreen(selectedTab = Tab.Squad, positionLists = barcelonaTeam) {}
    }
}
