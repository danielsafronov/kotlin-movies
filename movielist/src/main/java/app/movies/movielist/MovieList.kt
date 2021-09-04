package app.movies.movielist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

enum class MovieListMode {
    All,
    Favorites
}

@Composable
fun MovieList(mode: MovieListMode) {
    val viewModel: MovieListViewModel = hiltViewModel()
    viewModel.setMode(
        when(mode) {
            MovieListMode.All -> MovieListState.Mode.All
            MovieListMode.Favorites -> MovieListState.Mode.Favorites
        }
    )

    Content(
        viewModel = viewModel,
    )
}

@Composable
internal fun Content(
    viewModel: MovieListViewModel,
) {
    val state = viewModel.state.collectAsState()

    LazyColumn {
        items(5) {
            MovieCard()
        }
    }
}

@Composable
internal fun MovieCard(
    onShareClick: () -> Unit = {},
    onFavoriteClick: () -> Unit = {},
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "Movie Title")

            Text(text = "Movie Description")

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row {
                    TextButton(onClick = { onShareClick() }) {
                        Text(
                            text = "Share".uppercase(),
                        )
                    }

                    TextButton(onClick = { onFavoriteClick() }) {
                        Text(
                            text = "Add to favorite".uppercase(),
                        )
                    }
                }
            }
        }
    }
}
