package app.movies.data.resultmodel

import app.movies.data.model.FavoriteMovie
import app.movies.data.model.Movie

data class FavoriteWithMovie(
    val favorite: FavoriteMovie,
    val movie: Movie,
)
