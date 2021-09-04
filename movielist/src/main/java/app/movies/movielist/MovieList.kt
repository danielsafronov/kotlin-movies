package app.movies.movielist

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

enum class MovieListMode {
    All,
    Favorites
}

@Composable
fun MovieList(mode: MovieListMode) {
    Content(mode = mode)
}

@Composable
internal fun Content(mode: MovieListMode) {
    if (mode == MovieListMode.All) {
        Text(text = "All")
    } else {
        Text(text = "Favorites")
    }
}
