package app.movies.data.resultmodel

import app.movies.data.model.FavoriteMovie
import app.movies.data.model.Movie

data class MovieWithFavorite(
    val movie: Movie,
    val favorite: FavoriteMovie? = null,
)
