package app.movies.movielist

internal sealed class MovieListAction {
    data class AddToFavoriteAction(val movieId: Long): MovieListAction()
}
