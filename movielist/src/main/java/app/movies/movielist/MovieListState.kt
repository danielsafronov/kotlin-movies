package app.movies.movielist

internal data class MovieListState(
    val mode: Mode = Mode.All,
    val loading: Boolean = false,
) {
    companion object {
        val Empty = MovieListState()
    }

    internal enum class Mode {
        All,
        Favorites,
    }
}