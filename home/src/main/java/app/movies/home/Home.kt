package app.movies.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

internal object Tab {
    const val Films = 0
    const val Favorites = 1
}

@Composable
fun Home(
    films: @Composable () -> Unit = { },
    favorites: @Composable () -> Unit = { },
) {
    Content(
        films = films,
        favorites = favorites,
    )
}

@Composable
internal fun Content(
    films: @Composable () -> Unit = { },
    favorites: @Composable () -> Unit = { },
) {
    var selectedTabIndex by remember { mutableStateOf(Tab.Films) }

    val Content: @Composable () -> Unit = {
        when (selectedTabIndex) {
            Tab.Films -> films()
            Tab.Favorites -> favorites()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Movies")
                },
                elevation = 0.dp
            )
        }
    ) {
        Column {
            TabRow(
                selectedTabIndex = selectedTabIndex,
            ) {
                Tab(
                    selected = selectedTabIndex == Tab.Films,
                    onClick = { selectedTabIndex = Tab.Films },
                    text = { Text(text = "Films") }
                )

                Tab(
                    selected = selectedTabIndex == Tab.Favorites,
                    onClick = { selectedTabIndex = Tab.Favorites },
                    text = { Text("Favorites") },
                )
            }

            Content()
        }
    }
}