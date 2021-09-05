package app.movies.movielist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import app.movies.data.model.Movie
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

enum class MovieListMode {
    All,
    Favorites
}

@Composable
fun MovieList(mode: MovieListMode) {
    val viewModel: MovieListViewModel = hiltViewModel()
    viewModel.setMode(
        when (mode) {
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
    val pagingItems = viewModel.pagedMovies.collectAsLazyPagingItems()
    val state = viewModel.state.collectAsState()

    LazyColumn {
        itemsIndexed(pagingItems) { _, item ->
            item?.let {
                MovieCard(movie = it)
            }
        }
    }
}

@Composable
internal fun MovieCard(
    movie: Movie,
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
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier.weight(1F),
                    contentAlignment = Alignment.TopStart,
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        if (movie.posterUrl != null)
                            Poster(
                                url = movie.posterUrl!!,
                                modifier = Modifier.size(150.dp),
                            )

                        Text(
                            text = movie.rating.toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 8.dp),
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                Box(modifier = Modifier.weight(2F)) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = movie.title,
                            fontWeight = FontWeight.Bold,
                        )

                        Text(
                            text = movie.description,
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }

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

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun Poster(
    url: String,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = null,
        modifier = modifier,
        alignment = Alignment.TopStart,
    )
}
