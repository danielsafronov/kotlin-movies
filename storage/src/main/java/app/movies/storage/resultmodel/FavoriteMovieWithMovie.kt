package app.movies.storage.resultmodel

import androidx.room.Embedded
import androidx.room.Relation
import app.movies.storage.model.FavoriteMovieEntity
import app.movies.storage.model.MovieEntity

data class FavoriteMovieWithMovie(
    @Embedded
    val favoriteMovie: FavoriteMovieEntity,

    @Relation(parentColumn = "movie_id", entityColumn = "id")
    val movie: MovieEntity
)
