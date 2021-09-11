package app.movies.movielist

internal sealed class MovieListAction {
    data class AddToFavoriteAction(val movieId: Long): MovieListAction()
    data class RemoveFromFavorite(val favoriteMovieId: Long): MovieListAction()
}
