package app.movies.storage.resultmodel

import androidx.room.Embedded
import androidx.room.Relation
import app.movies.storage.model.FavoriteMovieEntity
import app.movies.storage.model.MovieEntity

data class MovieWithFavorite(
    @Embedded
    val movie: MovieEntity,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    val favorite: FavoriteMovieEntity? = null,
)
