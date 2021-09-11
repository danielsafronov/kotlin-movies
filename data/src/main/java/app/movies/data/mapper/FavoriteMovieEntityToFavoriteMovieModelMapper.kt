package app.movies.data.mapper

import app.movies.base.Mapper
import app.movies.data.model.FavoriteMovie
import app.movies.storage.model.FavoriteMovieEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteMovieEntityToFavoriteMovieModelMapper @Inject constructor(): Mapper<FavoriteMovieEntity, FavoriteMovie> {
    override suspend fun map(from: FavoriteMovieEntity): FavoriteMovie = FavoriteMovie(
        id = from.id,
        movieId = from.movieId,
    )
}
